package com.iridium.library.repository.power;

import com.iridium.library.entity.power.MagicEO;
import com.iridium.library.repository.BaseRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for magic entity.
 *
 * @author Kviatsinskaya Anastasia
 */
public interface MagicRepository extends BaseRepository<MagicEO, UUID> {

    String SPELLS = "spells";
    String ID = "id";

    /**
     * Find magic from db by magic id.
     *
     * @param magicId the magic id
     * @return the magic entity with spells
     */
    default Optional<MagicEO> findByIdWithSpells(final UUID magicId) {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<MagicEO> criteriaQuery = criteriaBuilder.createQuery(MagicEO.class);
        final Root<MagicEO> root = criteriaQuery.from(MagicEO.class);
        root.fetch(SPELLS);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(ID), magicId));
        return Optional.ofNullable(getEntityManager().createQuery(criteriaQuery).getSingleResult());
    }
}
