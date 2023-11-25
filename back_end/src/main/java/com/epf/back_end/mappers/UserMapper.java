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

    @Mapping(target = "rates", ignore = true)
    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);

    User userDTOToUser(UserDTORequest userDTORequest);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rates", ignore = true)
    void updateUserFromDTO(UserDTO userDTO, @MappingTarget User user);

    List<UserDTO> usersToUserDTOs(List<User> users);
    List<User> userDTOsToUsers(List<UserDTO> userDTOs);



    void updateUserFromDTO(UserDTORequest userDTORequest, @MappingTarget User user);


}
