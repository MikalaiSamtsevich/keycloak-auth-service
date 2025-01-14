package com.techopt.authservice.api;

import com.techopt.authservice.model.NewUserRecord;
import com.techopt.authservice.service.RoleService;
import com.techopt.authservice.service.UserService;
import com.techopt.authservice.util.KeycloakRole;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/users")
public class UserApi {

    public final UserService userService;
    public final RoleService roleService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/{id}/roles")
    public ResponseEntity<List<RoleRepresentation>> getRoles(@PathVariable String id) {
//        log.info(user.getName()); returns id of authenticated user
        return ResponseEntity.ok(roleService.getAllUserRoles(userService.findUserById(id)));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody NewUserRecord newUserRecord) {
        var createdUser = userService.createUser(newUserRecord);
        roleService.assignRole(KeycloakRole.USER, createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String username) {
        userService.forgotPassword(username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/send-verification-email/")
    public ResponseEntity<?> sendVerifyEmail(@PathVariable String id) {
        userService.sendVerificationEmail(id);
        return ResponseEntity.ok().build();
    }
}
