package com.techopt.authservice.service;

import com.techopt.authservice.util.KeycloakGroup;
import org.keycloak.admin.client.resource.UserResource;

public interface GroupService {
    void assignGroupToUser(KeycloakGroup keycloakGroup, UserResource userResource);


    void deleteGroupFromUser(KeycloakGroup keycloakGroup, UserResource userResource);
}
