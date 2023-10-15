package com.epf.ratingA.dao;

import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.Rate;
import com.epf.ratingA.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
    @Query("SELECT u.films FROM User u WHERE u.id= :userId")
    List<Film> getAllFilmsFromUser(Long id);
}
