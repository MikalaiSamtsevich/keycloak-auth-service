package com.techopt.authservice.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KeycloakGroup {
    CUSTOMER("CUSTOMER"),
    MANAGER("MANAGER");
    private final String group;
}
