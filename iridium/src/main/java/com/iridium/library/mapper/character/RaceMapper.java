package com.iridium.library.mapper.character;

import com.iridium.library.entity.character.RaceEO;
import com.iridium.openapi.model.Race;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RaceMapper {
    /**
     * Map race entity to race model.
     * @param raceEO race entity
     * @return race model
     */
    Race toRace(RaceEO raceEO);

    /**
     * Map race model to race entity.
     * @param race race model
     * @return race entity
     */
    RaceEO toRaceEO(Race race);

    /**
     * Map list of race entities to list of race models.
     * @param raceEOList list of race entities
     * @return list of race models
     */
    List<Race> toRaceList(List<RaceEO> raceEOList);
}
