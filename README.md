# spring-keycloak-api
 springboot keycloak implementation


run keycloak

`docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:23.0.3 start-dev`


access jwt endpoint:
http://localhost:8080/realms/spring-keycloak/.well-known/openid-configuration
login endpoint
http://localhost:8080/realms/spring-keycloak/protocol/openid-connect/auth?client_id=security-admin-console&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fadmin%2Fmaster%2Fconsole%2F%23%2Fspring-keycloak%2Frealm-settings%2Flogin&state=328d9215-b36b-4ee2-827e-d0bcab27d260&response_mode=fragment&response_type=code&scope=openid&nonce=9e4a9056-48df-4ae0-8f8f-8b405f7582af&code_challenge=1K6dy8o0_7CuAB3o_5GeUPkln1ul6UNwYKSevTa_oos&code_challenge_method=S256

* endpoint to create users x
* endpoint to delete users x
* protect api with admin role x
* protect api with user role x
* signup endpoint
* endpoint to get token
* test
* add new fields and read from jwt
* logout
* docker compose
* add keycloak to docker compose
* pre fetch keycloak users
* export roles
* add linter precommit
