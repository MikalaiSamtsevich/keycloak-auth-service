package com.techopt.authservice.service;

import com.techopt.authservice.model.NewUserRecord;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;

public interface UserService {

    UserResource createUser(NewUserRecord newUserRecord);

    void sendVerificationEmail(String userId);

    void deleteUser(String userId);

    void forgotPassword(String username);

    UserResource findUserById(String userId);
}
