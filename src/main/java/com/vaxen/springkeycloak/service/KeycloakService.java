package com.vaxen.springkeycloak.service;

import com.vaxen.springkeycloak.client.KeycloakTokenRequest;
import com.vaxen.springkeycloak.client.KeycloakTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;

@Service
public class KeycloakService {

    Logger log = LoggerFactory.getLogger(KeycloakService.class);
    private RestClient restClient;

    @Value("${admin.username}")
    private String username;

    @Value("${admin.password}")
    private String password;

    public KeycloakService(@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}") String keycloakUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(keycloakUrl)
                .build();
    }

    public void registerUser() {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public ResponseEntity<KeycloakTokenResponse> getAdminToken() {

        MultiValueMap<String, String> keycloakTokenRequest = new KeycloakTokenRequest().builder()
                .grant_type("password")
                .client_id("admin-cli")
                .username(username)
                .password(password)
                .build()
                .toMap();

        return restClient.post()
                .uri("/protocol/openid-connect/token")
                .contentType(APPLICATION_FORM_URLENCODED)
                .body(keycloakTokenRequest)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new RuntimeException("Error while getting token from keycloak");
                })
                .toEntity(KeycloakTokenResponse.class);
    }
}
