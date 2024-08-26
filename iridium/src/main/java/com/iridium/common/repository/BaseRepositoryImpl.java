package com.iridium.common.repository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;

/**
 * Base repository implementation.
 */
public class BaseRepositoryImpl<T, ID extends Serializable>
    extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;

    /**
     * Default settings for base repository.
     * @param entityInformation entityInformation
     * @param entityManager entityManager
     */
    public BaseRepositoryImpl(final JpaEntityInformation<T, ?> entityInformation, final EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    /**
     * Get entityManager.
     * @return entityManager
     */
    @Override
    public final EntityManager getEntityManager() {
        return this.entityManager;
    }
}
