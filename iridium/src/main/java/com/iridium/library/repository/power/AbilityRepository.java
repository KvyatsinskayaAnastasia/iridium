package com.iridium.library.repository.power;

import com.iridium.library.entity.power.AbilityEO;
import com.iridium.library.entity.power.AbilityTypeEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for ability entity.
 * @author Kviatsinskaya Anastasia
 */
@Repository
public interface AbilityRepository extends JpaRepository<AbilityEO, UUID> {
    /**
     * Find abilities from db by ability type.
     * @param abilityTypeEO the ability type for the search
     * @return the abilities entities by received ability type
     */
    List<AbilityEO> findByAbilityTypeIs(AbilityTypeEO abilityTypeEO);
}
