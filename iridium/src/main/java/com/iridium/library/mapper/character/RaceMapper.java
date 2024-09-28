package com.iridium.library.mapper.character;

import com.iridium.library.entity.character.RaceAbilityEO;
import com.iridium.library.entity.character.RaceEO;
import com.iridium.openapi.model.RaceAbility;
import com.iridium.openapi.model.RaceRequest;
import com.iridium.openapi.model.RaceResponse;
import com.iridium.openapi.model.ShortRaceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RaceMapper {
    /**
     * Map race entity to race model.
     *
     * @param raceEO race entity
     * @return race model
     */
    RaceResponse toRace(RaceEO raceEO);

    /**
     * Map race ability entity to race model.
     *
     * @param raceAbilityEO race ability entity
     * @return race ability model
     */
    @Mapping(target = "id", source = "ability.id")
    @Mapping(target = "name", source = "ability.name")
    RaceAbility toRace(RaceAbilityEO raceAbilityEO);

    /**
     * Map race model to race entity.
     *
     * @param race race request model
     * @return race entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "baseMagic", ignore = true)
    @Mapping(target = "unavailableMagic", ignore = true)
    @Mapping(target = "raceAbilities", ignore = true)
    RaceEO toRaceEO(RaceRequest race);

    /**
     * Map list of race entities to list of race models.
     *
     * @param raceEOList list of race entities
     * @return list of race models
     */
    List<ShortRaceResponse> toRaceList(List<RaceEO> raceEOList);
}
