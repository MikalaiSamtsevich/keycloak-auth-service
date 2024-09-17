package com.techopt.authservice.util;

import com.techopt.authservice.exception.model.InvalidStatusCodeException;
import jakarta.ws.rs.core.Response;

import java.util.Objects;
import java.util.Set;

public class StatusCodeValidator {
    public static final Set<Integer> VALID_STATUSES = Set.of(200, 201, 204);
    private StatusCodeValidator() {
    }

    public static void validate(Response response) {
        if (!VALID_STATUSES.contains(response.getStatus())) {
            throw new InvalidStatusCodeException("Status code " + response.getStatus());
        }
    }
}
