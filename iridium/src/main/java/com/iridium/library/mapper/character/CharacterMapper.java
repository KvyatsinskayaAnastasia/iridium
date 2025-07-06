package com.iridium.library.mapper.character;

import com.iridium.library.entity.character.CharacterAbilityEO;
import com.iridium.library.entity.character.CharacterEO;
import com.iridium.library.entity.power.SpellEO;
import com.iridium.openapi.model.CharacterMagic;
import com.iridium.openapi.model.Character;
import com.iridium.openapi.model.CharacterAbility;
import com.iridium.openapi.model.CharacterSpell;
import com.iridium.openapi.model.CreateCharacterRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

@Mapper(componentModel = "spring")
public abstract class CharacterMapper {
    private static final String ABSOLUTE_PATH = "%s\\%s";
    private static final String MAIN_DIRECTORY = "attaches";

    @Value("${attachments.directory}")
    private String directory;

    /**
     * Map character entity to character model.
     *
     * @param characterEO character entity
     * @return character model
     */
    public abstract Character toCharacter(CharacterEO characterEO);

    /**
     * Map character ability entity to character ability model.
     *
     * @param characterAbilityEO character ability entity
     * @return character ability model
     */
    @Mapping(target = "abilityId", source = "characterAbilityEO.ability.id")
    @Mapping(target = "abilityType", source = "characterAbilityEO.ability.abilityType")
    @Mapping(target = "abilityName", source = "characterAbilityEO.ability.name")
    public abstract CharacterAbility toCharacterAbility(CharacterAbilityEO characterAbilityEO);

    /**
     * Map character model to character entity.
     *
     * @param character character model
     * @return character entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "abilities", ignore = true)
    @Mapping(target = "spells", ignore = true)
    @Mapping(target = "race", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "image", ignore = true)
    public abstract CharacterEO toCharacterEO(CreateCharacterRequest character);

    /**
     * Map list of character entities to list of character models.
     *
     * @param characterEOList list of character entities
     * @return list of character models
     */
    public abstract List<Character> toCharacterList(List<CharacterEO> characterEOList);

    /**
     * Map set of spell entities to set of spell models.
     *
     * @param spellEOSet set of spell entities
     * @return set of spell models
     */
    public abstract Set<CharacterSpell> toSpellSet(Set<SpellEO> spellEOSet);

    @AfterMapping
    protected final void afterMapping(final CharacterEO characterEO,
                                      @MappingTarget final Character character) {
        character.setMagic(characterEO.getSpells().stream()
                .collect(Collectors.groupingBy(SpellEO::getMagic, mapping(Function.identity(), toSet())))
                .entrySet().stream().map(entry -> new CharacterMagic()
                        .id(entry.getKey().getId())
                        .name(entry.getKey().getName())
                        .spells(toSpellSet(entry.getValue())))
                .collect(toSet()));

        if (characterEO.getImage() != null) {
            character.setImageUrl(new ClassPathResource(MAIN_DIRECTORY + "/" + characterEO.getImage().getPath()
                    + "/" + characterEO.getImage().getName()));
        }
    }
}
