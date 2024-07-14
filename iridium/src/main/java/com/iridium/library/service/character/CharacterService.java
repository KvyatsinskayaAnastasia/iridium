package com.iridium.library.service.character;

import com.iridium.openapi.model.Character;

import java.util.List;
import java.util.UUID;

/**
 * Service to work with character model.
 *
 * @author Kviatsinskaya Anastasia
 *
 */
public interface CharacterService {
    /**
     * Save character in db.
     * @param character the model of a new character
     * @return the id of created character
     */
    UUID saveCharacter(Character character);

    /**
     * Get all characters from db.
     * @return the list of character responses
     */
    List<Character> getAllCharacters();

    /**
     * Get character by id from db.
     * @param id the id for the search
     * @return the character response by received id
     */
    Character getCharacterById(UUID id);
}
