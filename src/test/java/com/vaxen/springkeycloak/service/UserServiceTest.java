package com.vaxen.springkeycloak.service;

import com.vaxen.springkeycloak.client.UserCreateRequest;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private Keycloak keycloakAdminClient;

    @InjectMocks
    private UserService victim;

    @BeforeEach
    private void setup() {
        ReflectionTestUtils.setField(victim, "keycloakRealm", "dummy-realm");
    }


    @Test
    public void whenRegisterUserSuccess_thenReturns200ResponseEntity() {
        UserCreateRequest request = new UserCreateRequest();
        request.setEmail("test@example.com");
        givenKeycloakUserCreateEndpointReturningStatusCode(200);

        ResponseEntity<String> response = victim.registerUser(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("OK");
    }

    @Test
    public void whenRegisterUserFailed_thenReturns403ResponseEntity() {
        UserCreateRequest request = new UserCreateRequest();
        request.setEmail("test@example.com");
        givenKeycloakUserCreateEndpointReturningStatusCode(403);

        ResponseEntity<String> response = victim.registerUser(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(response.getBody()).isEqualTo("Forbidden");
    }

    private void givenKeycloakUserCreateEndpointReturningStatusCode(int statusCode) {
        RealmResource mockRealmResource = mock(RealmResource.class);
        UsersResource mockUsersResource = mock(UsersResource.class);
        when(keycloakAdminClient.realm(anyString())).thenReturn(mockRealmResource);
        when(mockRealmResource.users()).thenReturn(mockUsersResource);
        when(mockUsersResource.create(any(UserRepresentation.class))).thenReturn(Response.status(statusCode).build());
    }
}