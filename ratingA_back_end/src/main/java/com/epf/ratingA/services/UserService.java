package com.epf.ratingA.services;

import com.epf.ratingA.dao.UserDao;
import com.epf.ratingA.dto.UserDto;
import com.epf.ratingA.mappers.UserMapper;
import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> findAll(){return userDao.findAll();}
    public User finById(Long id){return userDao.findById(id).get();}
    public List<Film> findAllFilmsByUserId(Long userId){
        try {
            User user = userDao.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));

            return userDao.getAllFilmsFromUser(user.getId());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Film> findAllFilmsByUserName(String firstName, String lastName){
        try {
            User user = userDao.findUserByFirstNameAndLastName(firstName, lastName);

            if (user == null) {
                throw new NoSuchElementException("User not found");
            }
            return userDao.getAllFilmsFromUser(user.getId());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Transactional
    public void deleteById(Long userId){userDao.deleteById(userId);}

    @Transactional
    public Long createUser(UserDto userDto){
        try {
            User user = UserMapper.INSTANCE.fromUserDto(userDto, null);
            user = userDao.save(user);
            return user.getId();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        // return  (user != null) ? UserMapper.INSTANCE.toUserDto(user) : null;
    }

    @Transactional
    public UserDto getUserById(Long userId){
        try {
            User user = userDao.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));

            return UserMapper.INSTANCE.toUserDto(user);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
       // return  (user != null) ? UserMapper.INSTANCE.toUserDto(user) : null;
    }
    @Transactional
    public void updateUser(UserDto userDto, Long userId){
        userDao.findById(userId).orElseThrow(()->new NoSuchElementException("User doesn't exist"));

        try {
            User user = UserMapper.INSTANCE.fromUserDto(userDto,userId);
            userDao.save(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
