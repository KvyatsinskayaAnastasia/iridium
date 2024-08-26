package com.iridium.security.service.user;

import com.iridium.openapi.model.RegisterRequest;
import com.iridium.openapi.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends UserDetailsService {

    /**
     * Find user by user name.
     *
     * @param username user name
     * @return user model
     */
    User findUserByUsername(String username);

    /**
     * Save user in db.
     *
     * @param registerRequest register request
     * @return id of created user
     */
    UserDetails saveUser(RegisterRequest registerRequest);

    /**
     * Delete user from db by id.
     *
     * @param id user id
     */
    void deleteUser(UUID id);
}
