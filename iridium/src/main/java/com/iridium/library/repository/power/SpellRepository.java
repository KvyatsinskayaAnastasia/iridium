package com.iridium.library.repository.power;

import com.iridium.common.repository.BaseRepository;
import com.iridium.library.entity.power.SpellEO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
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
    Set<SpellEO> findByIdIn(Set<UUID> spellIds);

    /**
     * Find all spells for magic with the level less oe equal to max level.
     * @param magicId magic id
     * @param maxLevel max level of spells
     * @return spell entities
     */
    List<SpellEO> findByMagicIdAndLevelLessThanEqual(UUID magicId, int maxLevel);
}
