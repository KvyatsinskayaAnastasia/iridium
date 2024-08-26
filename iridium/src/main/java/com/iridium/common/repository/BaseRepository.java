package com.iridium.common.repository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Base interface for repositories, that provide access to EntityManager for child interfaces.
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    /**
     * Get entityManager.
     *
     * @return entityManager
     */
    EntityManager getEntityManager();
}
