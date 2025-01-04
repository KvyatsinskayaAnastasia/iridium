package com.iridium.library.mapper.character;

import com.iridium.library.entity.character.CharacterAbilityEO;
import com.iridium.library.entity.character.CharacterEO;
import com.iridium.openapi.model.Character;
import com.iridium.openapi.model.CharacterAbility;
import com.iridium.openapi.model.CreateCharacterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    /**
     * Map character entity to character model.
     * @param characterEO character entity
     * @return character model
     */
    Character toCharacter(CharacterEO characterEO);

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
    CharacterEO toCharacterEO(CreateCharacterRequest character);

    /**
     * Map list of character entities to list of character models.
     * @param characterEOList list of character entities
     * @return list of character models
     */
    List<Character> toCharacterList(List<CharacterEO> characterEOList);
}
