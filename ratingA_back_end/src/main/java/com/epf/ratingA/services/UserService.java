package com.epf.ratingA.services;

import com.epf.ratingA.dao.UserDao;
import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public List<User> findAll(){return userDao.findAll();}
    public User finById(Long id){return userDao.findById(id).get();}
    public List<Film> findAllFilmByUserId(Long id){return userDao.getAllFilmsFromUser(id);}
}
