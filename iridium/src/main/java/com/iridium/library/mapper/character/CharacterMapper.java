package com.iridium.library.mapper.character;

import com.iridium.library.entity.attachment.AttachmentEO;
import com.iridium.library.entity.character.CharacterAbilityEO;
import com.iridium.library.entity.character.CharacterEO;
import com.iridium.library.entity.power.SpellEO;
import com.iridium.openapi.model.CharacterMagic;
import com.iridium.openapi.model.Character;
import com.iridium.openapi.model.CharacterAbility;
import com.iridium.openapi.model.CharacterSpell;
import com.iridium.openapi.model.CreateCharacterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    /**
     * Map character entity to character model.
     * @param characterEO character entity
     * @return character model
     */
    @Mapping(expression = "java(toCharacterMagic(characterEO.getSpells()))", target = "magic")
    @Mapping(expression = "java(generateImageUrl(characterEO.getImage()))", target = "imageUrl")
    Character toCharacter(CharacterEO characterEO);

    /**
     * Generate url to image resource.
     * @param imageEO image entity
     * @return url to image
     */
    default Resource generateImageUrl(final AttachmentEO imageEO) {
        Path filePath = Paths.get(imageEO.getPath()).resolve(imageEO.getName());
        Resource resource = null;
        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        if (resource.exists()) {
            return resource;
        } else {
            return null;
        }
    }
    /**
     * Map spells to character magic model.
     * @param spellEOs spell entities
     * @return character magic list
     */
    default Set<CharacterMagic> toCharacterMagic(Set<SpellEO> spellEOs) {
       return spellEOs.stream()
               .collect(Collectors.groupingBy(SpellEO::getMagic, mapping(Function.identity(), toSet())))
               .entrySet().stream().map(entry -> new CharacterMagic()
                       .id(entry.getKey().getId())
                       .name(entry.getKey().getName())
                       .spells(toSpellSet(entry.getValue()))).collect(toSet());
    }

    /**
     * Map character ability entity to character ability model.
     * @param characterAbilityEO character ability entity
     * @return character ability model
     */
    @Mapping(target = "abilityId", source = "characterAbilityEO.ability.id")
    @Mapping(target = "abilityType", source = "characterAbilityEO.ability.abilityType")
    @Mapping(target = "abilityName", source = "characterAbilityEO.ability.name")
    CharacterAbility toCharacterAbility(CharacterAbilityEO characterAbilityEO);

    /**
     * Map character model to character entity.
     * @param character character model
     * @return character entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "abilities", ignore = true)
    @Mapping(target = "spells", ignore = true)
    @Mapping(target = "race", ignore = true)
    @Mapping(target = "userId", ignore = true)
    CharacterEO toCharacterEO(CreateCharacterRequest character);

    /**
     * Map list of character entities to list of character models.
     * @param characterEOList list of character entities
     * @return list of character models
     */
    List<Character> toCharacterList(List<CharacterEO> characterEOList);

    /**
     * Map set of spell entities to set of spell models.
     * @param spellEOSet set of spell entities
     * @return set of spell models
     */
    Set<CharacterSpell> toSpellSet(Set<SpellEO> spellEOSet);
}
