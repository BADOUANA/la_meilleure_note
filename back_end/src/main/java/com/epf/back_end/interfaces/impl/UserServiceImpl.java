package com.epf.back_end.interfaces.impl;

import com.epf.back_end.dao.UserDao;
import com.epf.back_end.dto.request.UserDTORequest;
import com.epf.back_end.dto.response.UserDTO;
import com.epf.back_end.exceptions.ResourceNotFoundException;
import com.epf.back_end.interfaces.UserService;
import com.epf.back_end.mappers.UserMapper;
import com.epf.back_end.models.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final UserMapper userMapper;

    public UserDTO getUserById(Long id) throws ResourceNotFoundException {
        User user = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.userToUserDTO(user);
    }

    public List<UserDTO> getAllUsers() throws ResourceNotFoundException {
        List<User> users = userDao.findAll();
        List<UserDTO> dtoResponses = new ArrayList<>();

        for (User user : users) {
            UserDTO dtoResponse = userMapper.userToUserDTO(user);
            dtoResponses.add(dtoResponse);
        }
        return dtoResponses;
    }

    public UserDTO createUser(UserDTORequest userDTORequest) throws Exception {
        // You might want to add validation logic here before creating the user
        try {
            User user = userMapper.userDTOToUser(userDTORequest);
            User savedUser = userDao.save(user);
            return userMapper.userToUserDTO(savedUser);
        }catch (Exception e){
            throw new RuntimeException();
        }


    }

    public UserDTO updateUser(Long id, UserDTORequest userDTORequest) throws ResourceNotFoundException {
        // Similar to create, you might want to add validation logic here
        User existingUser = userDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        // Update the existingUser fields with data from userDTO
        userMapper.updateUserFromDTO(userDTORequest, existingUser);


        User updatedUser = userDao.save(existingUser);
        return userMapper.userToUserDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }
}
