package com.iridium.library.repository.character;

import com.iridium.common.repository.BaseRepository;
import com.iridium.library.entity.character.RaceAbilityEO;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for race entity.
 *
 * @author Kviatsinskaya Anastasia
 */
@Repository
public interface RaceAbilityRepository extends BaseRepository<RaceAbilityEO, UUID> {
}
