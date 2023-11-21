package com.epf.ratingA.dao;

import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    User findUserByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT r FROM Rate r WHERE r.user.id= :userId")
    List<Film> getAllRatesFromUser(@Param("userId")Long userId);
}
