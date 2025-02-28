package com.iridium.library.service.character;

import com.iridium.openapi.model.Character;
import com.iridium.openapi.model.CreateCharacterRequest;
import com.iridium.openapi.model.PreGeneratedCharacter;

import java.util.List;
import java.util.UUID;

/**
 * Service to work with character model.
 * @author Kviatsinskaya Anastasia
 */
public interface CharacterService {
    /**
     * Save character in db.
     * @param character the model of a new character
     * @return the id of created character
     */
    UUID saveCharacter(CreateCharacterRequest character);

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

    /**
     * Delete character by id from db.
     *
     * @param uuid id of character to delete
     */
    void deleteCharacter(UUID uuid);

    /**
     * Generate character data.
     * @return pre generated character model
     */
    PreGeneratedCharacter generateCharacter();
}
