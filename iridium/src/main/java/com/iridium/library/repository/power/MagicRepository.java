package com.iridium.library.repository.power;

import com.iridium.library.entity.power.MagicEO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository interface for magic entity.
 * @author Kviatsinskaya Anastasia
 */
public interface MagicRepository extends JpaRepository<MagicEO, UUID> {
}
