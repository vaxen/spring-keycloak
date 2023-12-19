# spring-keycloak-api
 springboot keycloak implementation


run keycloak

`docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:23.0.3 start-dev`


access jwt endpoint:
http://localhost:8080/realms/SPRING-KEYCLOAK/.well-known/openid-configuration


* endpoint to create users
* endpoint to delete users
* protect api with admin role
* protect api with user role
* generate jwt token and add fields
