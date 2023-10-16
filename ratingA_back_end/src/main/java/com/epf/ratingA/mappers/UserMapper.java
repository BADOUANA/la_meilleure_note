package com.epf.ratingA.mappers;

import com.epf.ratingA.dto.UserDto;
import com.epf.ratingA.models.User;

import java.io.IOException;


public class UserMapper {
    public static UserDto fromDto(User user)throws IOException {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthdate(user.getBirthdate())
                .sexe(user.getSexe())
                .email(user.getEmail())
                .role(user.isRole())
                .build();
    }
}
