package com.techopt.authservice.api;

import com.techopt.authservice.service.GroupService;
import com.techopt.authservice.service.UserService;
import com.techopt.authservice.util.KeycloakGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/groups")
public class GroupApi {

    private final GroupService groupService;
    private final UserService userService;

    @PutMapping("/{userId}")
    public ResponseEntity<Void> assignGroupToUser(@PathVariable("userId") String userId, @RequestParam("group") KeycloakGroup keycloakGroup) {
        groupService.assignGroupToUser(keycloakGroup, userService.findUserById(userId));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> deleteGroupFromUser(@PathVariable("user-id") String userId, @RequestParam("group") KeycloakGroup keycloakGroup) {
        groupService.deleteGroupFromUser(keycloakGroup, userService.findUserById(userId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
