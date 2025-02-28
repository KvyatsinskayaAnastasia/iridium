package com.iridium.library.service.character.impl;

import com.iridium.library.entity.character.CharacterAbilityEO;
import com.iridium.library.entity.character.RaceEO;
import com.iridium.library.entity.power.MagicEO;
import com.iridium.library.mapper.character.CharacterMapper;
import com.iridium.library.model.character.AgesStage;
import com.iridium.library.model.character.CharacterLevel;
import com.iridium.library.repository.character.CharacterAbilityRepository;
import com.iridium.library.repository.character.CharacterRepository;
import com.iridium.library.service.character.CharacterService;
import com.iridium.library.service.character.RaceService;
import com.iridium.library.service.power.AbilityService;
import com.iridium.library.service.power.MagicService;
import com.iridium.openapi.model.AbilityType;
import com.iridium.openapi.model.CharacterAbility;
import com.iridium.openapi.model.CharacterMagic;
import com.iridium.openapi.model.Character;
import com.iridium.openapi.model.CreateCharacterRequest;
import com.iridium.openapi.model.Gender;
import com.iridium.openapi.model.PreGeneratedCharacter;
import com.iridium.openapi.model.ShortMagicResponse;
import com.iridium.security.service.user.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.iridium.common.utils.CommonRandomUtils.randomEnum;
import static com.iridium.common.utils.CommonRandomUtils.randomFromList;
import static com.iridium.library.utils.character.CharacterGeneratorUtils.generateName;
import static com.iridium.library.utils.character.CharacterGeneratorUtils.generateTemper;
import static java.util.stream.Collectors.toMap;

/**
 * Base service to work with character model.
 * See the {@link com.iridium.library.service.character.CharacterService}.
 *
 * @author Kviatsinskaya Anastasia
 */
@Service
@RequiredArgsConstructor
public class BaseCharacterService implements CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterAbilityRepository characterAbilityRepository;
    private final CharacterMapper characterMapper;

    private final RaceService raceService;
    private final AbilityService abilityService;
    private final MagicService magicService;

    private final AuthorizationService authorizationService;

    @Override
    public final UUID saveCharacter(final CreateCharacterRequest character) {
        final var newCharacter = characterMapper.toCharacterEO(character);
        newCharacter.setUserId(authorizationService.getCurrentUser().getId());
        newCharacter.setRace(raceService.getRaceById(character.getRaceId(), RaceEO.class));
        newCharacter.setSpells(magicService.getAllSpellsEntitiesByIds(character.getSpells()));
        final var savedCharacter = characterRepository.save(newCharacter);

        final var abilitiesMap = character.getAbilities().stream()
            .collect(toMap(CharacterAbility::getAbilityId, Function.identity()));

        final var abilityEOs = abilityService.getAllAbilityEntitiesByIds(abilitiesMap.keySet());
        final var characterAbilitiesEO = abilityEOs.stream().map(abilityEO -> {
            final CharacterAbilityEO characterAbilityEO = new CharacterAbilityEO();
            characterAbilityEO.setAbility(abilityEO);
            characterAbilityEO.setLevel(abilitiesMap.get(abilityEO.getId()).getLevel());
            characterAbilityEO.setCharacter(savedCharacter);
            return characterAbilityEO;
        }).collect(Collectors.toSet());
        characterAbilityRepository.saveAll(characterAbilitiesEO);

        return savedCharacter.getId();
    }

    @Override
    public final List<Character> getAllCharacters() {
        return characterMapper.toCharacterList(characterRepository.findAll());
    }

    @Override
    public final Character getCharacterById(final UUID id) {
        return characterMapper.toCharacter(characterRepository.findByIdWithSpellsAndAbilities(id).orElse(null));
    }

    @Override
    public final void deleteCharacter(final UUID uuid) {
        characterRepository.deleteById(uuid);
    }

    @Override
    public final PreGeneratedCharacter generateCharacter() {
        final PreGeneratedCharacter preGeneratedCharacter = new PreGeneratedCharacter();
        final var races = raceService.getAllRaces();
        preGeneratedCharacter.setRaceId(races.get(RandomUtils.nextInt(0, races.size() - 1)).getId());
        final var agesStage = randomEnum(AgesStage.class);
        preGeneratedCharacter.setAge(RandomUtils.nextInt(agesStage.getMinAges(), agesStage.getMaxAges()));
        preGeneratedCharacter.setGender(randomEnum(Gender.class));
        preGeneratedCharacter.setName(generateName(preGeneratedCharacter.getGender()));
        preGeneratedCharacter.setTemper(generateTemper());
        final var characterLevel = AgesStage.CHILD == agesStage
            ? CharacterLevel.SMALL : randomEnum(CharacterLevel.class);
        final var magicCount = RandomUtils.nextInt(0, characterLevel.getMaxFightSkillsCount());
        final var fightAbilityCount = characterLevel.getMaxFightSkillsCount() - magicCount;
        preGeneratedCharacter.setMagics(generateMagics(preGeneratedCharacter.getRaceId(), characterLevel, magicCount));
        preGeneratedCharacter.setAbilities(generateAbilities(characterLevel, fightAbilityCount));
        return preGeneratedCharacter;
    }

    private Set<CharacterAbility> generateAbilities(final CharacterLevel characterLevel, final int fightAbilityCount) {
        final Set<CharacterAbility> characterAbilities = new HashSet<>();
        final var abilities = abilityService.getAllAbilities();
        characterAbilities.addAll(abilities.stream()
            .filter(ability -> AbilityType.BASIC == ability.getAbilityType())
            .map(ability -> new CharacterAbility()
                .abilityId(ability.getId())
                .abilityName(ability.getName())
                .abilityType(ability.getAbilityType())
                .level(RandomUtils.nextInt(characterLevel.getMinAbilityLevel(), characterLevel.getMaxAbilityLevel())))
            .collect(Collectors.toSet()));
        characterAbilities.addAll(abilities.stream()
            .filter(ability -> AbilityType.GENERAL == ability.getAbilityType() && RandomUtils.nextBoolean())
            .map(ability -> new CharacterAbility()
                .abilityId(ability.getId())
                .abilityName(ability.getName())
                .abilityType(ability.getAbilityType())
                .level(RandomUtils.nextInt(characterLevel.getMinAbilityLevel(), characterLevel.getMaxAbilityLevel())))
            .collect(Collectors.toSet()));
        characterAbilities.addAll(randomFromList(abilities.stream()
            .filter(ability -> AbilityType.FIGHTING == ability.getAbilityType())
            .toList(), fightAbilityCount).stream()
            .map(ability -> new CharacterAbility()
                .abilityId(ability.getId())
                .abilityName(ability.getName())
                .abilityType(ability.getAbilityType())
                .level(RandomUtils.nextInt(characterLevel.getMinAbilityLevel(), characterLevel.getMaxAbilityLevel())))
            .collect(Collectors.toSet()));
        return characterAbilities;
    }

    private Set<CharacterMagic> generateMagics(final UUID raceId, final CharacterLevel characterLevel,
                                               final int magicCount) {
        final var race = raceService.getRaceById(raceId, RaceEO.class);
        final var unavailableMagic = race.getUnavailableMagic();
        final var magicIds = unavailableMagic != null
            ? magicService.getAllMagicIdsNotIn(unavailableMagic.stream()
                .map(MagicEO::getId).collect(Collectors.toSet()))
            : magicService.getAllMagic().stream().map(ShortMagicResponse::getId).toList();
        final var baseMagicId = race.getBaseMagic().stream().map(MagicEO::getId).toList()
            .get(RandomUtils.nextInt(0, race.getBaseMagic().size() - 1));
        final var secondaryMagicIds = randomFromList(magicIds.stream()
            .filter(magicId -> !baseMagicId.equals(magicId)).toList(), magicCount);
        final Set<CharacterMagic> characterMagics = new HashSet<>();
        characterMagics.add(new CharacterMagic()
            .id(baseMagicId)
            .spells(magicService.getAllCharacterSpellsForMagic(baseMagicId,
                RandomUtils.nextInt(1, characterLevel.getMaxMagicLevel()))));
        for (final var secondaryMagicId : secondaryMagicIds) {
            characterMagics.add(new CharacterMagic()
                .id(secondaryMagicId)
                .spells(magicService.getAllCharacterSpellsForMagic(
                    secondaryMagicId, RandomUtils.nextInt(1, characterLevel.getMaxMagicLevel()))));
        }
        return characterMagics;
    }
}
