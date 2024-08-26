package com.iridium.security.repository.user;

import com.iridium.security.entity.user.UserEO;
import com.iridium.common.repository.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends BaseRepository<UserEO, UUID> {
    /**
     * Find user by user name.
     * @param username user name
     * @return user from db
     */
    Optional<UserEO> findByUsername(String username);
}
