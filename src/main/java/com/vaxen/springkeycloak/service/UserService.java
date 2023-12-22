package com.vaxen.springkeycloak.service;

import com.vaxen.springkeycloak.client.KeycloakTokenResponse;
import com.vaxen.springkeycloak.client.UserCreateRequest;
import jakarta.ws.rs.NotSupportedException;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    Logger log = LoggerFactory.getLogger(UserService.class);

    @Qualifier("keycloakAdminClient")
    private final Keycloak keycloakAdminClient;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    public UserService(Keycloak keycloakAdminClient) {
        this.keycloakAdminClient = keycloakAdminClient;
        System.out.println("keycloakRealm: " + keycloakRealm);
    }

    public ResponseEntity<String> registerUser(UserCreateRequest request) {
        UserRepresentation user = new UserRepresentation();
        user.setEmailVerified(true);
        user.setEmail(request.getEmail());
        user.setEnabled(true);
        //convert to optional?
        Response response = keycloakAdminClient.realm(keycloakRealm).users().create(user);
        return new ResponseEntity(response.getStatusInfo().toString(), HttpStatusCode.valueOf(response.getStatus()));
    }

    public KeycloakTokenResponse issueToken() {
        throw new NotSupportedException("Not implemented yet");
    }
}
