# spring-keycloak-api
 springboot keycloak implementation


run keycloak

`docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:23.0.3 start-dev`


access jwt endpoint:
http://localhost:8080/realms/spring-keycloak/.well-known/openid-configuration

* endpoint to create users x
* endpoint to delete users x
* protect api with admin role x
* protect api with user role x
* try keycloak admin library x
* signup endpoint x
* endpoint to get token
* test
* add new fields and read from jwt
* logout
* docker compose
* add keycloak to docker compose
* pre fetch keycloak users
* export roles
* add linter precommit
