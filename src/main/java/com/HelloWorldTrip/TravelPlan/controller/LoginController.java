package com.HelloWorldTrip.TravelPlan.controller;

import com.HelloWorldTrip.TravelPlan.dto.LoginRequest;
import com.HelloWorldTrip.TravelPlan.dto.LoginResponse;
import com.HelloWorldTrip.TravelPlan.enums.ApiMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication API", description = "API for user authentication")
public class LoginController {

    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Authenticates a user with userId and password")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserId(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok(new LoginResponse(ApiMessage.USER_AUTHENTICATED_SUCCESS));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(ApiMessage.INVALID_USERID_OR_PASSWORD.getStatus())
                    .body(new LoginResponse(ApiMessage.INVALID_USERID_OR_PASSWORD));
        }
    }
}
