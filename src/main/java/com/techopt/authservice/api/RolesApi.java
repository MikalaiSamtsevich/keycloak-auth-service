package com.techopt.authservice.api;

import com.techopt.authservice.service.RoleService;
import com.techopt.authservice.service.UserService;
import com.techopt.authservice.util.KeycloakRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.OncePerRequestFilter;

@RestController
@Slf4j
@RequestMapping("/auth/roles")
@RequiredArgsConstructor
public class RolesApi {

    private final UserService userService;
    private final RoleService roleService;
    OncePerRequestFilter oncePerRequestFilter;

    @PutMapping("/{user-id}")
    public ResponseEntity<?> assignRole(@PathVariable("user-id") String userId,
                                        @RequestParam("role") KeycloakRole keycloakRole) {
        roleService.assignRole(keycloakRole, userService.findUserById(userId));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> deleteRole(@PathVariable("user-id") String userId,
                                        @RequestParam("role") KeycloakRole keycloakRole) {
        roleService.deleteRoleFromUser(keycloakRole, userService.findUserById(userId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
