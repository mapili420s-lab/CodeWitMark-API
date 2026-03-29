package com.project.codewithmark.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ROLE_CLIENT("Client"),
    ROLE_THERAPIST("Therapist"),
    ROLE_ADMIN("Admin");

    private final String name;

}
