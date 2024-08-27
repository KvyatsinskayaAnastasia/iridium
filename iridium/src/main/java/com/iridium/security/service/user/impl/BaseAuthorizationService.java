package com.iridium.security.service.user.impl;

import com.iridium.openapi.model.LoginRequest;
import com.iridium.openapi.model.LoginResponse;
import com.iridium.openapi.model.RegisterRequest;
import com.iridium.openapi.model.User;
import com.iridium.security.service.JwtService;
import com.iridium.security.service.user.AuthorizationService;
import com.iridium.security.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseAuthorizationService implements AuthorizationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public final LoginResponse login(final LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginRequest.getUsername(),
            loginRequest.getPassword()
        ));
        final var token = jwtService.generateToken(userService.loadUserByUsername(loginRequest.getUsername()));
        return new LoginResponse().token(token);
    }

    @Override
    public final String register(final RegisterRequest registerRequest) {
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        return jwtService.generateToken(
            userService.saveUser(registerRequest)
        );
    }

    @Override
    public final User currentUser() {
        if (null != SecurityContextHolder.getContext()
            && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails user) {
            return userService.findUserByUsername(user.getUsername());
        }
        return null;
    }
}
