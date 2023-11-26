package com.epf.back_end.mappers;

import com.epf.back_end.dto.request.UserDTORequest;
import com.epf.back_end.dto.response.UserDTO;
import com.epf.back_end.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring", implementationName = "UserMapperImpl")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);

    User userDTOToUser(UserDTORequest userDTORequest);

    void updateUserFromDTO(UserDTORequest userDTORequest, @MappingTarget User user);


}
