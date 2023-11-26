package com.epf.back_end.dto.response;

import com.epf.back_end.enumer.Role;
import com.epf.back_end.models.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Instant birthdate;
    private String sex;
    private String password;
    private List<RateDTO> rates;
    private Image image;
}
