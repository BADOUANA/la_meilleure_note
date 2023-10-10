package com.epf.ratingA.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private boolean role;
    private String email;
    private Instant birthdate;
    private String sexe;
    private String password;
}
