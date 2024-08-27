package com.iridium.security.controller;

import com.iridium.openapi.model.LoginResponse;
import com.iridium.openapi.model.RegisterRequest;
import com.iridium.openapi.model.User;
import com.iridium.security.service.user.AuthorizationService;
import com.iridium.openapi.api.AuthorizationApi;
import com.iridium.openapi.model.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthorizationController implements AuthorizationApi {
    private final AuthorizationService authorizationService;

    @Override
    public final ResponseEntity<LoginResponse> login(final LoginRequest loginRequest) {
        authorizationService.login(loginRequest);
        return ResponseEntity.ok(authorizationService.login(loginRequest));
    }

    @Override
    public final ResponseEntity<String> register(final RegisterRequest registerRequest) {
        return ResponseEntity.ok(authorizationService.register(registerRequest));
    }

    @Override
    public final ResponseEntity<User> currentUser() {
        return ResponseEntity.ok(authorizationService.currentUser());
    }
}
