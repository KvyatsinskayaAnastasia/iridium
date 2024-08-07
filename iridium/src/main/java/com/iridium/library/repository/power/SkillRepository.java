package com.iridium.library.repository.power;

import com.iridium.library.entity.power.SkillEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for skill entity.
 *
 * @author Kviatsinskaya Anastasia
 */
@Repository
public interface SkillRepository extends JpaRepository<SkillEO, UUID> {

    /**
     * Find skills from db by skill ids.
     *
     * @param skillIds the list of skill ids for the search
     * @return the skills entities by received skill ids
     */
    List<SkillEO> findByIdIn(List<UUID> skillIds);
}
