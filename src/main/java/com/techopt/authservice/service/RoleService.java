package com.techopt.authservice.service;

import com.techopt.authservice.util.KeycloakRole;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;

public interface RoleService {

    void assignRole(KeycloakRole keycloakRoles, UserResource userResource);

    void deleteRoleFromUser(KeycloakRole keycloakRole, UserResource userResource);

    List<RoleRepresentation> getAllUserRoles(UserResource userResource);
}
