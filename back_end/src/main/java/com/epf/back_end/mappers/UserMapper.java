package com.epf.back_end.mappers;

import com.epf.back_end.dto.UserDTO;
import com.epf.back_end.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring", implementationName = "UserMapperImpl")
public interface UserMapper {
    /*public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .birthdate(user.getBirthdate())
                .sex(user.getSex())
                .rates(RateMapper.toDTOList(user.getRates()))
                .build();
    }
    public static User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .role(userDTO.getRole())
                .birthdate(userDTO.getBirthdate())
                .sex(userDTO.getSex())
                // Exclude rates, as it's likely you don't want to set them directly from the DTO
                .build();
    }

    public static List<User> toEntityList(List<UserDTO> userDTOS) {
        return userDTOS.stream().map(UserMapper::toEntity).collect(Collectors.toList());
    }*/
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(target = "rates", ignore = true)
    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rates", ignore = true)
    void updateUserFromDTO(UserDTO userDTO, @MappingTarget User user);

    List<UserDTO> usersToUserDTOs(List<User> users);
    List<User> userDTOsToUsers(List<UserDTO> userDTOs);

}
