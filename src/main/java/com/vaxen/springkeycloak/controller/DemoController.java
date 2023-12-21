package com.vaxen.springkeycloak.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api/v1/demo")
public class DemoController {

    @GetMapping("/hello")
    @PreAuthorize("hasAnyRole('spring_keycloak_user', 'spring_keycloak_admin')")
    public String hello(){
        return "Hello from Spring Keycloak!";
    }

    @GetMapping("/hello-2")
    @PreAuthorize("hasRole('spring_keycloak_admin')")
    public String hello2(){
        return "Hello from Spring Keycloak! ADMIN";
    }

    //roles defined in client settings
    private final String SPRING_KEYCLOAK_ADMIN = "spring_keycloak_admin";
    private final String SPRING_KEYCLOAK_USER = "spring_keycloak_user";
}
