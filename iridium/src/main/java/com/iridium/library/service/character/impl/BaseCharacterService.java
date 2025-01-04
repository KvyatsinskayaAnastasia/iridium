package com.iridium.library.service.character.impl;

import com.iridium.library.entity.character.CharacterAbilityEO;
import com.iridium.library.entity.character.RaceEO;
import com.iridium.library.mapper.character.CharacterMapper;
import com.iridium.library.repository.character.CharacterAbilityRepository;
import com.iridium.library.repository.character.CharacterRepository;
import com.iridium.library.service.character.CharacterService;
import com.iridium.library.service.character.RaceService;
import com.iridium.library.service.power.AbilityService;
import com.iridium.library.service.power.MagicService;
import com.iridium.openapi.model.Character;
import com.iridium.openapi.model.CharacterAbility;
import com.iridium.openapi.model.CreateCharacterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
 * Base service to work with character model.
 * See the {@link com.iridium.library.service.character.CharacterService}.
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

    @Override
    public final UUID saveCharacter(final CreateCharacterRequest character) {
        final var newCharacter = characterMapper.toCharacterEO(character);
        newCharacter.setRace(raceService.getRaceById(character.getRaceId(), RaceEO.class));
        newCharacter.setSpells(magicService.getAllSpellsEntitiesByIds(character.getSpells()));
        final var savedCharacter = characterRepository.save(newCharacter);

        final var abilitiesMap = character.getAbilities().stream()
            .collect(toMap(CharacterAbility::getAbilityId, Function.identity()));
        final var abilityEOs = abilityService.getAllAbilityEntitiesByIds(abilitiesMap.keySet());
        final var characterAbilitiesEO = abilityEOs.stream().map(abilityEO -> {
                final CharacterAbilityEO characterAbilityEO = new CharacterAbilityEO();
                characterAbilityEO.setCharacter(savedCharacter);
                characterAbilityEO.setAbility(abilityEO);
                characterAbilityEO.setLevel(abilitiesMap.get(abilityEO.getId()).getLevel());
                return characterAbilityRepository.save(characterAbilityEO);
            }).collect(Collectors.toSet());
        newCharacter.setAbilities(characterAbilitiesEO);

        return characterRepository.save(savedCharacter).getId();
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
}
