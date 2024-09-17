package com.techopt.authservice.model;

public record NewUserRecord(String username,
                            String password,
                            String passwordConfirm,
                            String email,
                            String firstname,
                            String lastname) {
}
