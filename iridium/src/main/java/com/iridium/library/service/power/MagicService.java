package com.iridium.library.service.power;

import com.iridium.library.entity.power.MagicEO;
import com.iridium.library.entity.power.SpellEO;
import com.iridium.openapi.model.AddMagicRequest;
import com.iridium.openapi.model.MagicResponse;
import com.iridium.openapi.model.ShortMagicResponse;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Service to work with magic model.
 *
 * @author Kviatsinskaya Anastasia
 */
public interface MagicService {
    /**
     * Save magic in db.
     *
     * @param addMagicRequest the model of a new magic
     * @return the id of the created magic
     */
    UUID saveMagic(AddMagicRequest addMagicRequest);

    /**
     * Get all magic from db.
     *
     * @return the list of magic responses
     */
    List<ShortMagicResponse> getAllMagic();

    /**
     * Get magic by id from db.
     *
     * @param id the id for the search
     * @return the magic response by received id
     */
    MagicResponse getMagicById(UUID id);

    /**
     * Delete magic by id from db.
     *
     * @param uuid id of magic to delete
     */
    void deleteMagic(UUID uuid);

    /**
     * Get all magic by ids.
     *
     * @param magicIds magic ids
     * @return magic entities
     */
    Set<MagicEO> getAllMagicEntitiesByIds(Set<UUID> magicIds);

    /**
     * Get all spells by ids.
     * @param spells spell ids
     * @return spell entities
     */
    Set<SpellEO> getAllSpellsEntitiesByIds(Set<UUID> spells);
}
