package com.iridium.library.repository.character;

import com.iridium.common.repository.BaseRepository;
import com.iridium.library.entity.character.CharacterEO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for character entity.
 * @author Kviatsinskaya Anastasia
 */
@Repository
public interface CharacterRepository extends BaseRepository<CharacterEO, UUID> {
    String SPELLS = "spells";
    String ABILITIES = "abilities";
    String ID = "id";

    /**
     * Find character by id.
     * @param id character id
     * @return result with spells and abilities
     */
    default Optional<CharacterEO> findByIdWithSpellsAndAbilities(final UUID id) {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<CharacterEO> criteriaQuery = criteriaBuilder.createQuery(CharacterEO.class);
        final Root<CharacterEO> root = criteriaQuery.from(CharacterEO.class);
        root.fetch(SPELLS, JoinType.LEFT);
        root.fetch(ABILITIES, JoinType.LEFT);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(ID), id));
        return Optional.ofNullable(getEntityManager().createQuery(criteriaQuery).getSingleResult());
    }

}
