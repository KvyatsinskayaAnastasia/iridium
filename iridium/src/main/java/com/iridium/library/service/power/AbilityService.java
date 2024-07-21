package com.iridium.library.service.power;

import com.iridium.openapi.model.Ability;
import com.iridium.openapi.model.AbilityType;
import com.iridium.openapi.model.AddAbility;
import com.iridium.openapi.model.ShortAbilityResponse;

import java.util.List;
import java.util.UUID;

/**
 * Service to work with ability model.
 * @author Kviatsinskaya Anastasia
 */
public interface AbilityService {
    /**
     * Save ability in db.
     * @param ability the model of a new ability
     * @return the id of created ability
     */
    UUID saveAbility(AddAbility ability);

    /**
     * Get all abilities from db.
     * @return the list of short ability responses
     */
    List<ShortAbilityResponse> getAllAbilities();

    /**
     * Get ability by id from db.
     * @param id the id for the search
     * @return the ability response by received id
     */
    Ability getAbilityById(UUID id);

    /**
     * Get all abilities by type from db.
     * @param type the ability type for the search
     * @return the list of short ability responses filtered by type
     */
    List<ShortAbilityResponse> getAllAbilitiesByType(AbilityType type);

    /**
     * Save ability in db.
     * @param uuid id of ability to delete
     */
    void deleteAbility(UUID uuid);
}
