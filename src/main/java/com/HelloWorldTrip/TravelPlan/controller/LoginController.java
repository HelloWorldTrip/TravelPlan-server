package com.HelloWorldTrip.TravelPlan.controller;

import com.HelloWorldTrip.TravelPlan.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@Tag(name = "Destination", description = "The Destination API")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/example")
    @Operation(summary = "Get example", description = "This is Get example")
    public ResponseEntity<?> getExample() {
        return ResponseEntity.ok("");
    }

    @PostMapping
    @Operation(summary = "Post example", description = "This is Post example")
    public ResponseEntity<?> postExample(){
        return ResponseEntity.ok("");
    }

    @PutMapping
    @Operation(summary = "Put example", description = "This is Put example")
    public ResponseEntity<?> putExample(){
        return ResponseEntity.ok("");
    }

    @PatchMapping
    @Operation(summary = "Patch example", description = "This is Patch example")
    public ResponseEntity<?> patchExample(){
        return ResponseEntity.ok("");
    }

    @DeleteMapping
    @Operation(summary = "Delete example", description = "This is Delete example")
    public ResponseEntity<?> deleteExample(){
        return ResponseEntity.ok("");
    }
}
