package com.msb.embeddedbanking.controller;

import com.msb.embeddedbanking.api.AuthApi;
import com.msb.embeddedbanking.controller.request.auth.LoginRequest;
import com.msb.embeddedbanking.controller.request.auth.RefreshTokenRequest;
import com.msb.embeddedbanking.controller.response.auth.LoginResponse;
import com.msb.embeddedbanking.facade.AuthFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController implements AuthApi {
    private final AuthFacade authFacade;

    public AuthController(AuthFacade authFacade) {
        this.authFacade = authFacade;
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        log.info("Start logging in request for username: {}", loginRequest.getUsername());

        LoginResponse response = authFacade.login(loginRequest);

        log.info("Successfully logging in for username {}", loginRequest.getUsername());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<LoginResponse> refreshToken(RefreshTokenRequest comMsbEmbeddedbankingControllerRequestAuthRefreshTokenRequest) {
        return null;
    }
}
