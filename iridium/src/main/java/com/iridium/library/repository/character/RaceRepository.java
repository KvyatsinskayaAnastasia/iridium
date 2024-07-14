package com.iridium.library.repository.character;

import com.iridium.library.entity.character.RaceEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for race entity.
 * @author Kviatsinskaya Anastasia
 */
@Repository
public interface RaceRepository extends JpaRepository<RaceEO, UUID> {
}
