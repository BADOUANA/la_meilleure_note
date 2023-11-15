package com.epf.ratingA.mappers;

import com.epf.ratingA.dto.UserDto;
import com.epf.ratingA.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.io.IOException;

@Mapper
public interface UserMapper {
    static UserDto fromDto(User user)throws IOException {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthdate(user.getBirthdate())
                .sexe(user.getSexe())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toUserDto(User user)throws IOException;

    User fromUserDto(UserDto userDto, Long id)throws IOException;

}
