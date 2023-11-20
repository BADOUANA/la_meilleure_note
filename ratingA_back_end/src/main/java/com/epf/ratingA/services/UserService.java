package com.epf.ratingA.services;

import com.epf.ratingA.dao.UserDao;
import com.epf.ratingA.dto.UserDto;
import com.epf.ratingA.exceptions.UserException;
import com.epf.ratingA.mappers.UserMapper;
import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }


    public List<User> findAll(){return userDao.findAll();}
    public UserDto getUserById(Long userId) {
        Optional<User> userOptional = userDao.findById(userId);
        return userOptional.map(userMapper::userToUserDto).orElseThrow(()->
                new UserException("User not found"));
    }
    public List<Film> findAllFilmsByUserId(Long userId) throws UserException{
        try {
            User user = userDao.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));

            return userDao.getAllFilmsFromUser(user.getId());
        }catch (Exception e){
            e.printStackTrace();
            throw new UserException("Problem when acces to get list");
        }
    }

    public List<Film> findAllFilmsByUserName(String firstName, String lastName){
        try {
            User user = userDao.findUserByFirstNameAndLastName(firstName, lastName);

            if (user == null) {
                throw new UserException("User not found with name: " + firstName + " " + lastName);
            }

            return userDao.getAllFilmsFromUser(user.getId());
        } catch (DataAccessException e) {
            // Handle database-related exceptions
            throw new RuntimeException("Error accessing data", e);
        } catch (Exception e) {
            // Handle other unexpected exceptions
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }
    @Transactional
    public void deleteById(Long userId){userDao.deleteById(userId);}

    @Transactional
    public UserDto createUser(UserDto userDto){
        try {
            User user = userMapper.userDtoToUser(userDto);
            User savedUser = userDao.save(user);
            return userMapper.userToUserDto(savedUser);
        }catch (Exception e){
            e.printStackTrace();
            throw new UserException("Error creating user", e);        }
        // return  (user != null) ? UserMapper.INSTANCE.toUserDto(user) : null;
    }

    @Transactional
    public UserDto updateUser(UserDto userDto, Long userId){
        User user = userDao.findById(userId).orElseThrow(()->new NoSuchElementException("User doesn't exist"));

        try {
            userMapper.updateUserFromDto(userDto, user);
            userDao.save(user);
            return userDto;
        }catch (Exception e){
            e.printStackTrace();
            throw new UserException("Error updating user", e);
        }

    }
}
