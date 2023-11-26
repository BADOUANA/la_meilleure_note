package com.epf.back_end.interfaces;

import com.epf.back_end.dto.request.UserDTORequest;
import com.epf.back_end.dto.response.UserDTO;
import com.epf.back_end.exceptions.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    UserDTO getUserById(Long userId) throws ResourceNotFoundException;

    List<UserDTO> getAllUsers() throws ResourceNotFoundException;

    UserDTO createUser(UserDTORequest userDTORequest) throws RuntimeException;

    UserDTO updateUser(Long userId, UserDTORequest userDTORequest) throws ResourceNotFoundException;

    void deleteUser(Long userId);

    void updateUserImage(Long id, MultipartFile image) throws IOException;

}
