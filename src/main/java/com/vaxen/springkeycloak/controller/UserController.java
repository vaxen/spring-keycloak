package com.vaxen.springkeycloak.controller;

import com.vaxen.springkeycloak.client.UserCreateRequest;
import com.vaxen.springkeycloak.service.UserService;
import com.vaxen.springkeycloak.validator.UserRegistrationValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService keycloakService;

    public UserController(UserService keycloakService) {
        this.keycloakService = keycloakService;
    }

    @PostMapping(value = "/signup")
    @PreAuthorize("hasAnyRole('spring_keycloak_user', 'spring_keycloak_admin')")
    public ResponseEntity signup(@RequestBody UserCreateRequest userCreateRequest) {
        UserRegistrationValidator.EmailValidationEnum result = UserRegistrationValidator.isEmailValid()
        .apply(userCreateRequest);

    if (result != UserRegistrationValidator.EmailValidationEnum.SUCCESS) {
        return new ResponseEntity<>(result.name(), HttpStatus.BAD_REQUEST);
    }

    return keycloakService.registerUser(userCreateRequest);
    }
}
