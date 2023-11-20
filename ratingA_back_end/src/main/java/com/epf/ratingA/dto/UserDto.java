package com.epf.ratingA.dto;

import com.epf.ratingA.enumer.Role;
import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.Rate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Role role;
    private String email;
    private Instant birthdate;
    private String sexe;
    private String password;
    private List<Film> films;
    private List<Rate> rates;
}
