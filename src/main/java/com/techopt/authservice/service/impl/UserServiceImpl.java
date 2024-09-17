package com.techopt.authservice.service.impl;

import com.techopt.authservice.model.NewUserRecord;
import com.techopt.authservice.service.UserService;
import com.techopt.authservice.util.StatusCodeValidator;
import com.techopt.authservice.util.KeycloakEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersResource usersResource;

    private final UserRepresentation userRepresentation;

    private final CredentialRepresentation credentialRepresentation;

    public UserRepresentation mapUser(NewUserRecord newUserRecord){
        userRepresentation.setEnabled(true);
        userRepresentation.setUsername(newUserRecord.username());
        userRepresentation.setEmail(newUserRecord.email());
        userRepresentation.setFirstName(newUserRecord.username());
        userRepresentation.setLastName(newUserRecord.lastname());
        userRepresentation.setCreatedTimestamp(System.currentTimeMillis());
        userRepresentation.setEmailVerified(false);

        credentialRepresentation.setValue(newUserRecord.password());
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        userRepresentation.setCredentials(List.of(credentialRepresentation));
        return userRepresentation;
    }

    @Override
    public UserResource createUser(NewUserRecord newUserRecord) {
        mapUser(newUserRecord);

        try (var response = usersResource.create(userRepresentation)) {
            log.info("RESPONSE: {}", response.getEntity());
            StatusCodeValidator.validate(response);
            log.info("User {} has been created", newUserRecord.firstname());
        }
        var createdUser = usersResource.search(newUserRecord.username()).get(0);
        sendVerificationEmail(createdUser.getId());
        return findUserById(createdUser.getId());
    }

    @Override
    public void sendVerificationEmail(String userId) {
        usersResource.get(userId).sendVerifyEmail();
    }

    @Override
    public void deleteUser(String userId) {
        try (var response = usersResource.delete(userId)) {
            StatusCodeValidator.validate(response);
        }
    }

    @Override
    public void forgotPassword(String username) {
        UserResource userResource = usersResource.get(usersResource.search(username).get(0).getId());

        userResource.executeActionsEmail(List.of(KeycloakEvent.UPDATE_PASSWORD.getEvent()));
    }

    @Override
    public UserResource findUserById(String userId) {
        return usersResource.get(userId);
    }
}
