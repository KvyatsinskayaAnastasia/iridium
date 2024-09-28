package com.iridium.library.repository.character;

import com.iridium.common.repository.BaseRepository;
import com.iridium.library.entity.character.RaceEO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for race entity.
 *
 * @author Kviatsinskaya Anastasia
 */
@Repository
public interface RaceRepository extends BaseRepository<RaceEO, UUID> {
    String BASE_MAGIC = "baseMagic";
    String UNAVAILABLE_MAGIC = "unavailableMagic";
    String RACE_ABILITIES = "raceAbilities";
    String ID = "id";

    /**
     * Find race from db by ability id with race info.
     *
     * @param raceId the race id
     * @return the race entity with magic and bonuses info
     */
    default Optional<RaceEO> findByIdWithMagicInfoAndAbilityBonuses(final UUID raceId) {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<RaceEO> criteriaQuery = criteriaBuilder.createQuery(RaceEO.class);
        final Root<RaceEO> root = criteriaQuery.from(RaceEO.class);
        root.fetch(BASE_MAGIC, JoinType.LEFT);
        root.fetch(UNAVAILABLE_MAGIC, JoinType.LEFT);
        root.fetch(RACE_ABILITIES, JoinType.LEFT);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(ID), raceId));
        return Optional.ofNullable(getEntityManager().createQuery(criteriaQuery).getSingleResult());
    }
}
