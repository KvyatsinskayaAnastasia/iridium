package com.iridium.library.repository.character;

import com.iridium.library.entity.character.CharacterEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for character entity.
 * @author Kviatsinskaya Anastasia
 */
@Repository
public interface CharacterRepository extends JpaRepository<CharacterEO, UUID> {
}
