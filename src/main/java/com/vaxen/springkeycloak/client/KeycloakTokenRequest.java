package com.vaxen.springkeycloak.client;

import lombok.Builder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;

@Builder
public class KeycloakTokenRequest implements Serializable {
    private static final long serialVersionUID = 2L;

    private String grant_type;
    private String client_id;
    private String client_secret;
    private String username;
    private String password;

    public KeycloakTokenRequest() {
    }

    public KeycloakTokenRequest(String grant_type, String client_id, String client_secret, String username, String password) {
        this.grant_type = grant_type;
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.username = username;
        this.password = password;
    }

    public MultiValueMap<String, String> toMap() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", grant_type);
        formData.add("client_id", client_id);
        formData.add("client_secret", client_secret);
        formData.add("username", username);
        formData.add("password", password);
        return formData;
    }

}
