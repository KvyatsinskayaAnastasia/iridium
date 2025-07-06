package com.iridium.library.service.character;

import com.iridium.openapi.model.Character;
import com.iridium.openapi.model.CreateCharacterRequest;
import com.iridium.openapi.model.PreGeneratedCharacter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
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
     * @param image character image
     * @return the id of created character
     * @throws IOException exception
     */
    UUID saveCharacter(CreateCharacterRequest character, MultipartFile image) throws IOException;

    /**
     * Get all characters from db.
     * @return the list of character responses
     */
    List<Character> getAllCharacters() throws MalformedURLException;

    /**
     * Get character by id from db.
     * @param id the id for the search
     * @return the character response by received id
     */
    Character getCharacterById(UUID id) throws MalformedURLException;

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
