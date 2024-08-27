package com.iridium.security.service.user.impl;

import com.iridium.openapi.model.RegisterRequest;
import com.iridium.openapi.model.User;
import com.iridium.security.entity.user.RoleNameEO;
import com.iridium.security.entity.user.UserEO;
import com.iridium.security.mapper.user.UserMapper;
import com.iridium.security.repository.user.RoleRepository;
import com.iridium.security.repository.user.UserRepository;
import com.iridium.security.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

/**
 * Service to work with user model.
 *
 * @author Kviatsinskaya Anastasia
 */
@Service
@RequiredArgsConstructor
public class BaseUserService implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    /**
     * Load user details by user name.
     *
     * @param username user name
     * @return user details
     * @throws UsernameNotFoundException if we haven't user with received user name
     */
    @Override
    public final UserEO loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
    }

    @Override
    public final User findUserByUsername(final String username) {
        return userMapper.toUser(
            userRepository.findByUsername(username).orElseThrow(RuntimeException::new)
        );
    }

    @Override
    public final UserDetails saveUser(final RegisterRequest registerRequest) {
        final var userEO = userRepository.findByUsername(registerRequest.getUsername()).orElse(null);
        if (null != userEO) {
            throw new RuntimeException("Такой пользователь уже есть в системе!");
            //todo: add exceptions and error responses
        }
        final var roleEO = roleRepository.findByName(RoleNameEO.ROLE_USER);
        final var newUserEO = userMapper.toUserEO(registerRequest);
        newUserEO.setRoles(Collections.singleton(roleEO));
        return userRepository.save(newUserEO);
    }

    @Override
    public final void deleteUser(final UUID id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }
}
