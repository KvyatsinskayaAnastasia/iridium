package com.iridium.library.repository.power;

import com.iridium.library.entity.power.AbilityEO;
import com.iridium.library.entity.power.AbilityTypeEO;
import com.iridium.common.repository.BaseRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for ability entity.
 *
 * @author Kviatsinskaya Anastasia
 */
@Repository
public interface AbilityRepository extends BaseRepository<AbilityEO, UUID> {

    String SKILLS = "skills";
    String ID = "id";

    /**
     * Find abilities from db by ability type.
     *
     * @param abilityTypeEO the ability type for the search
     * @return the abilities entities by received ability type
     */
    List<AbilityEO> findByAbilityTypeIs(AbilityTypeEO abilityTypeEO);

    /**
     * Find ability from db by ability id.
     *
     * @param abilityId the ability id
     * @return the ability entity with skills
     */
    default Optional<AbilityEO> findByIdWithSkills(final UUID abilityId) {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<AbilityEO> criteriaQuery = criteriaBuilder.createQuery(AbilityEO.class);
        final Root<AbilityEO> root = criteriaQuery.from(AbilityEO.class);
        root.fetch(SKILLS, JoinType.LEFT);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(ID), abilityId));
        return Optional.ofNullable(getEntityManager().createQuery(criteriaQuery).getSingleResult());
    }
}
