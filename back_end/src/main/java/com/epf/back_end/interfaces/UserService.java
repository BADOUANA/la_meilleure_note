package com.epf.back_end.interfaces;

import com.epf.back_end.dto.UserDTO;
import com.epf.back_end.exceptions.ResourceNotFoundException;

import java.util.List;

public interface UserService {
    UserDTO getUserById(Long userId) throws ResourceNotFoundException;

    List<UserDTO> getAllUsers() throws ResourceNotFoundException;

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Long userId, UserDTO userDTO) throws ResourceNotFoundException;

    void deleteUser(Long userId);

}
