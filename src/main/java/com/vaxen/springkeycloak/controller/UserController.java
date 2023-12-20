package com.vaxen.springkeycloak.controller;

import com.vaxen.springkeycloak.client.KeycloakTokenResponse;
import com.vaxen.springkeycloak.service.KeycloakService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final KeycloakService keycloakService;

    public UserController(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    @PostMapping(value = "/signup")
    @PreAuthorize("hasAnyRole('spring_keycloak_user', 'spring_keycloak_admin')")
    public ResponseEntity<KeycloakTokenResponse> signup() {
        return keycloakService.getAdminToken();
    }
}
