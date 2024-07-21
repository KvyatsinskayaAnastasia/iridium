package com.iridium.library.service.power;

import com.iridium.openapi.model.Magic;
import com.iridium.openapi.model.MagicResponse;

import java.util.List;
import java.util.UUID;

/**
 * Service to work with magic model.
 * @author Kviatsinskaya Anastasia
 */
public interface MagicService {
    /**
     * Save magic in db.
     * @param magic the model of a new magic
     * @return the id of the created magic
     */
    UUID saveMagic(Magic magic);

    /**
     * Get all magic from db.
     * @return the list of magic responses
     */
    List<MagicResponse> getAllMagic();

    /**
     * Get magic by id from db.
     * @param id the id for the search
     * @return the magic response by received id
     */
    MagicResponse getMagicById(UUID id);
}
