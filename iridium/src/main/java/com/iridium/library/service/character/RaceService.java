package com.iridium.library.service.character;

import com.iridium.openapi.model.RaceRequest;
import com.iridium.openapi.model.ShortRaceResponse;

import java.util.List;
import java.util.UUID;

/**
 * Service to work with character model.
 *
 * @author Kviatsinskaya Anastasia
 */
public interface RaceService {
    /**
     * Save race in db.
     *
     * @param race the model of a new race
     * @return the id of created race
     */
    UUID saveRace(RaceRequest race);

    /**
     * Get all races from db.
     *
     * @return the list of race responses
     */
    List<ShortRaceResponse> getAllRaces();

    /**
     * Get race by id from db.
     *
     * @param id id for the search
     * @param type class of the type of response model
     * @param <T> type of response model
     * @return race response by received id and type
     */
    <T> T getRaceById(UUID id, Class<T> type);

    /**
     * Delete race by id from db.
     *
     * @param uuid id of race to delete
     */
    void deleteRace(UUID uuid);
}
