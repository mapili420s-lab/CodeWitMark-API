package com.project.codewithmark.dto.therapist_dto;

import com.project.codewithmark.model.enums.AccountStatus;
import com.project.codewithmark.model.enums.TherapistStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TherapistResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private AccountStatus accountStatus;
    private TherapistStatus status;
}
