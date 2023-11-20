package com.epf.ratingA.mappers;

import com.epf.ratingA.dto.UserDto;
import com.epf.ratingA.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "films", ignore = true)
    @Mapping(target = "rates", ignore = true)
    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    void updateUserFromDto(UserDto userDto, @MappingTarget User user);


}
