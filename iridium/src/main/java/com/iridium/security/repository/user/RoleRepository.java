package com.iridium.security.repository.user;

import com.iridium.security.entity.user.RoleEO;
import com.iridium.security.entity.user.RoleNameEO;
import com.iridium.common.repository.BaseRepository;

import java.util.UUID;

public interface RoleRepository extends BaseRepository<RoleEO, UUID> {
    /**
     * Find role by role name.
     *
     * @param name role name
     * @return role from db
     */
    RoleEO findByName(RoleNameEO name);
}
