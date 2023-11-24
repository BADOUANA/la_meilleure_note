package com.epf.back_end.interfaces;

import com.epf.back_end.dto.request.UserDTORequest;
import com.epf.back_end.dto.response.UserDTO;
import com.epf.back_end.exceptions.ResourceNotFoundException;

import java.util.List;

public interface UserService {
    UserDTO getUserById(Long userId) throws ResourceNotFoundException;

    List<UserDTO> getAllUsers() throws ResourceNotFoundException;

    UserDTO createUser(UserDTORequest userDTORequest) throws Exception;

    UserDTO updateUser(Long userId, UserDTORequest userDTORequest) throws ResourceNotFoundException;

    void deleteUser(Long userId);

}
