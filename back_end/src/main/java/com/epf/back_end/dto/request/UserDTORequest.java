package com.epf.back_end.dto.request;

import com.epf.back_end.enumer.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class UserDTORequest {
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Instant birthdate;
    private String sex;
    private String password;
    private Long imageId;
}
