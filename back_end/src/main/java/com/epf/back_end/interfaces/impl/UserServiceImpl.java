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
            UserDTO userDTO = userMapper.userToUserDTO(user);
            dtoResponses.add(userDTO);
        }
        return dtoResponses;
    }

    @Override
    public UserDTO createUser(UserDTORequest userDTORequest) throws RuntimeException {
        try {
            User user = userMapper.userDTOToUser(userDTORequest);
            User savedUser = userDao.save(user);
            return userMapper.userToUserDTO(savedUser);
        }catch (Exception e){
            throw new RuntimeException();
        }

    }

    @Override
    public UserDTO updateUser(Long id, UserDTORequest userDTORequest) throws ResourceNotFoundException {
        User existingUser = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Update the existingUser fields with data from userDTO
        userMapper.updateUserFromDTO(userDTORequest, existingUser);


        User updatedUser = userDao.save(existingUser);
        return userMapper.userToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }
}
