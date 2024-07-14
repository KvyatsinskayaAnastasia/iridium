package com.iridium.library.mapper.character;

import com.iridium.library.entity.character.CharacterEO;
import com.iridium.openapi.model.Character;
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
    @Mapping(target = "race", ignore = true)
    Character toCharacter(CharacterEO characterEO);

    /**
     * Map character model to character entity.
     * @param character character model
     * @return character entity
     */
    @Mapping(target = "raceEO", ignore = true)
    CharacterEO toCharacterEO(Character character);

    /**
     * Map list of character entities to list of character models.
     * @param characterEOList list of character entities
     * @return list of character models
     */
    List<Character> toCharacterList(List<CharacterEO> characterEOList);
}
