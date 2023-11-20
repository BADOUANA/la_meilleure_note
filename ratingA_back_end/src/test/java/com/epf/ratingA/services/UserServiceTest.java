package com.epf.ratingA.services;

import com.epf.ratingA.dao.UserDao;
import com.epf.ratingA.dto.UserDto;
import com.epf.ratingA.exceptions.UserException;
import com.epf.ratingA.mappers.UserMapper;
import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
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
    void testFindAllFilmsByUserName() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";

        User user = new User();
        user.setId(1L);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        List<Film> films = Arrays.asList(new Film(), new Film());

        when(userDao.findUserByFirstNameAndLastName(firstName, lastName)).thenReturn(user);
        when(userDao.getAllFilmsFromUser(user.getId())).thenReturn(films);

        // Act
        List<Film> result = userService.findAllFilmsByUserName(firstName, lastName);

        // Assert
        assertEquals(films, result);
        verify(userDao, times(1)).findUserByFirstNameAndLastName(firstName, lastName);
        verify(userDao, times(1)).getAllFilmsFromUser(user.getId());
    }

    @Test
    void testFindAllFilmsByUserId() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Film film1 = new Film();
        film1.setId(1L);
        film1.setTitle("Film 1");

        Film film2 = new Film();
        film2.setId(2L);
        film2.setTitle("Film 2");

        List<Film> films = Arrays.asList(film1, film2);

        when(userDao.findById(userId)).thenReturn(Optional.of(user));
        when(userDao.getAllFilmsFromUser(userId)).thenReturn(films);

        // Act
        List<Film> result = userService.findAllFilmsByUserId(userId);

        // Assert
        assertEquals(films, result);
        verify(userDao, times(1)).findById(userId);
        verify(userDao, times(1)).getAllFilmsFromUser(userId);
    }
  /*  @Test
    void testGetUserById() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        UserDto userDto = new UserDto();
        userDto.setId(userId);

        when(userDao.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.userToUserDto(user)).thenReturn(userDto);

        // Act
        UserDto result = userService.getUserById(userId);

        // Assert
        assertEquals(userId, result.getId());
        verify(userDao, times(1)).findById(userId);
        verify(userMapper, times(1)).userToUserDto(user);
    }

    @Test
    void testGetUserByIdNotFound() {
        // Arrange
        Long userId = 1L;

        when(userDao.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserException.class, () -> userService.getUserById(userId));
        verify(userDao, times(1)).findById(userId);
        verify(userMapper, never()).userToUserDto(any());
    }

    @Test
    void testFindAllFilmsByUserId() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        Film film = new Film();
        List<Film> films = Collections.singletonList(film);

        when(userDao.findById(userId)).thenReturn(Optional.of(user));
        when(userDao.getAllFilmsFromUser(userId)).thenReturn(films);

        // Act
        List<Film> result = userService.findAllFilmsByUserId(userId);

        // Assert
        assertEquals(films, result);
        verify(userDao, times(1)).findById(userId);
        verify(userDao, times(1)).getAllFilmsFromUser(userId);
    }

    @Test
    void testFindAllFilmsByUserIdNotFound() {
        // Arrange
        Long userId = 1L;

        when(userDao.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserException.class, () -> userService.findAllFilmsByUserId(userId));
        verify(userDao, times(1)).findById(userId);
        verify(userDao, never()).getAllFilmsFromUser(any());
    }



    @Test
    public void testCreateUser() {
        // Mocking the behavior of the userDao.save method
        when(userDao.save(any())).thenReturn(new User());

        // Mocking the behavior of the userMapper.userToUserDto method
        when(userMapper.userToUserDto(any())).thenReturn(new UserDto());

        // Calling the method to be tested
        UserDto userDto = userService.createUser(new UserDto());

        // Assertions
        assertNotNull(userDto);
        verify(userDao, times(1)).save(any());
        verify(userMapper, times(1)).userToUserDto(any());
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
        when(userMapper.updateUserFromDto(updatedUserDto, existingUser)).thenAnswer(invocation -> {
            UserDto dtoArg = invocation.getArgument(0);
            User userArg = invocation.getArgument(1);

            // Custom logic for updating the user from the DTO
            userArg.setFirstName(dtoArg.getFirstName());
            userArg.setLastName(dtoArg.getLastName());
            // Update other properties as needed

            return null; // The updateUserFromDto method is void
        });

        // Act
        UserDto result = userService.updateUser(updatedUserDto, userId);

        // Assert
        assertEquals(updatedUserDto, result);
        verify(userDao, times(1)).findById(userId);
        verify(userMapper, times(1)).updateUserFromDto(updatedUserDto, existingUser);
        verify(userDao, times(1)).save(existingUser);
    }

    @Test
    public void testFindAllFilmsByUserName_Success() {
        // Mocking the behavior of the userDao.findUserByFirstNameAndLastName method
        when(userDao.findUserByFirstNameAndLastName(any(), any())).thenReturn(new User());

        // Mocking the behavior of the userDao.getAllFilmsFromUser method
        when(userDao.getAllFilmsFromUser(anyLong())).thenReturn(Collections.emptyList());

        // Calling the method to be tested
        List<Film> films = userService.findAllFilmsByUserName("John", "Doe");

        // Assertions
        assertNotNull(films);
        assertTrue(films.isEmpty());
        verify(userDao, times(1)).findUserByFirstNameAndLastName("John", "Doe");
        verify(userDao, times(1)).getAllFilmsFromUser(anyLong());
    }*/

}
