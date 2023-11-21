package com.epf.ratingA.services;

import com.epf.ratingA.dao.UserDao;
import com.epf.ratingA.dto.UserDto;
import com.epf.ratingA.mappers.UserMapper;
import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;


    @Test
    void testFindAll() {
        // Arrange
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("John");
        // Set other properties as needed

        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Jane");
        // Set other properties as needed

        List<User> userList = Arrays.asList(user1, user2);

        when(userDao.findAll()).thenReturn(userList);

        // Act
        List<User> result = userService.findAll();

        // Assert
        assertEquals(userList, result);
        verify(userDao, times(1)).findAll();
    }

    @Test
    void testFindByUserName() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";

        User user = new User();
        user.setId(1L);
        user.setFirstName(firstName);
        user.setLastName(lastName);


        when(userDao.findUserByFirstNameAndLastName(firstName, lastName)).thenReturn(user);

        // Act
        Long result = userService.findByUserName(firstName, lastName);

        // Assert
        assertEquals(user.getId(), result);
        verify(userDao, times(1)).findUserByFirstNameAndLastName(firstName, lastName);}

    @Test
    void testCreateUser() {
        // Arrange
        UserDto newUserDto = new UserDto();
        newUserDto.setId(1L);
        newUserDto.setFirstName("John");
        newUserDto.setLastName("Doe");
        // Set other properties as needed

        User newUser = new User();
        newUser.setId(1L);
        // Set other properties as needed

        when(userMapper.userDtoToUser(newUserDto)).thenReturn(newUser);
        when(userDao.save(newUser)).thenReturn(newUser);
        when(userMapper.userToUserDto(newUser)).thenReturn(newUserDto);

        // Act
        UserDto result = userService.createUser(newUserDto);

        // Assert
        assertEquals(newUserDto, result);
        verify(userMapper, times(1)).userDtoToUser(newUserDto);
        verify(userDao, times(1)).save(newUser);
        verify(userMapper, times(1)).userToUserDto(newUser);
    }

    @Test
    void testUpdateUser() {
        // Arrange
        Long userId = 1L;
        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setId(userId);
        updatedUserDto.setFirstName("John");
        updatedUserDto.setLastName("Doe");
        // Set other properties as needed

        User existingUser = new User();
        existingUser.setId(userId);
        // Set other properties as needed

        when(userDao.findById(userId)).thenReturn(Optional.of(existingUser));
        doNothing().when(userMapper).updateUserFromDto(updatedUserDto, existingUser);
        when(userDao.save(existingUser)).thenReturn(existingUser);

        // Act
        UserDto result = userService.updateUser(updatedUserDto, userId);

        // Assert
        assertEquals(updatedUserDto, result);
        verify(userDao, times(1)).findById(userId);
        verify(userMapper, times(1)).updateUserFromDto(updatedUserDto, existingUser);
        verify(userDao, times(1)).save(existingUser);
    }

    @Test
    void testDeleteById() {
        // Arrange
        Long userId = 1L;
        User existingUser = new User();
        existingUser.setId(userId);
        // Set other properties as needed

        when(userDao.findById(userId)).thenReturn(Optional.of(existingUser));

        // Act
        userService.deleteById(userId);

        // Assert
        verify(userDao, times(1)).deleteById(userId);
    }
}
