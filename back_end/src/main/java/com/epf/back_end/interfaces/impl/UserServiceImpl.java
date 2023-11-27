package com.epf.back_end.interfaces.impl;

import com.epf.back_end.dao.ImageDao;
import com.epf.back_end.dao.UserDao;
import com.epf.back_end.dto.request.UserDTORequest;
import com.epf.back_end.dto.response.RateDTO;
import com.epf.back_end.dto.response.UserDTO;
import com.epf.back_end.exceptions.ResourceNotFoundException;
import com.epf.back_end.interfaces.ImageService;
import com.epf.back_end.interfaces.UserService;
import com.epf.back_end.mappers.UserMapper;
import com.epf.back_end.models.Image;
import com.epf.back_end.models.Rate;
import com.epf.back_end.models.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional // Transactional annotation ensures that each method is wrapped by a transaction
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final UserMapper userMapper;
    private final ImageService imageService;


    @Override
    public UserDTO getUserById(Long id) throws ResourceNotFoundException {
        User user = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.userToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() throws ResourceNotFoundException {
        // Retrieve all users from the database
        List<User> users = userDao.findAll();
        // Map each User entity to UserDTO and collect in a list
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
        // Find the existing user by ID or throw an exception if not found

        User existingUser = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Update the existingUser fields with data from userDTO
        userMapper.updateUserFromDTO(userDTORequest, existingUser);

        // Save the updated user to the database and return the mapped UserDTO
        User updatedUser = userDao.save(existingUser);
        return userMapper.userToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public void updateUserImage(Long id, MultipartFile image) throws  IOException {
        User existingUser = userDao.findById(id).orElseThrow(()->new RuntimeException("User not found"+id));
        // Get the existing image associated with the user
        Image existingImage = existingUser.getImage();
        if (existingImage != null){
            imageService.updateImage(existingImage.getId(),image);
        }else {
            throw new RuntimeException();
        }

    }
}
