package com.epf.back_end.interfaces.impl;

import com.epf.back_end.dao.UserDao;
import com.epf.back_end.dto.UserDTO;
import com.epf.back_end.exceptions.ResourceNotFoundException;
import com.epf.back_end.interfaces.UserService;
import com.epf.back_end.mappers.UserMapper;
import com.epf.back_end.models.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public UserDTO getUserById(Long id) throws ResourceNotFoundException {
        User user = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.userToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() throws ResourceNotFoundException {
        List<User> users = userDao.findAll();
        List<UserDTO> dtoResponses = new ArrayList<>();

        for (User user : users) {
            UserDTO dtoResponse = userMapper.userToUserDTO(user);
            dtoResponses.add(dtoResponse);
        }
        return dtoResponses;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // You might want to add validation logic here before creating the user
        User user = userMapper.userDTOToUser(userDTO);
        User savedUser = userDao.save(user);
        return userMapper.userToUserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) throws ResourceNotFoundException {
        // Similar to create, you might want to add validation logic here
        User existingUser = userDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        // Update the existingUser fields with data from userDTO
        userMapper.updateUserFromDTO(userDTO, existingUser);


        User updatedUser = userDao.save(existingUser);
        return userMapper.userToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }
}
