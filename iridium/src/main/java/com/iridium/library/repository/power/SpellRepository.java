package com.iridium.library.repository.power;

import com.iridium.library.entity.power.SpellEO;
import com.iridium.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for ability entity.
 *
 * @author Kviatsinskaya Anastasia
 */
@Repository
public interface SpellRepository extends BaseRepository<SpellEO, UUID> {

    /**
     * Find spells from db by spell ids.
     *
     * @param spellIds the list of spell ids for the search
     * @return the spell entities by received spell ids
     */
    List<SpellEO> findByIdIn(List<UUID> spellIds);
}
