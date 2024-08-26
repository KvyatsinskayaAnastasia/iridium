package com.iridium.security.service.user;

import com.iridium.openapi.model.LoginRequest;
import com.iridium.openapi.model.LoginResponse;
import com.iridium.openapi.model.RegisterRequest;

public interface AuthorizationService {
    /**
     * Login.
     * @param loginRequest login request
     * @return jwt token
     */
    LoginResponse login(LoginRequest loginRequest);

    /**
     * Register.
     * @param registerRequest register request
     * @return jwt token
     */
    String register(RegisterRequest registerRequest);
}
