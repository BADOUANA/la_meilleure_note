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

    User findUserByFirstNameAndLastName(String firstName, String lastName);

    //@Query("SELECT f FROM Film f JOIN Rate r ON f.idFilm = r.film.idFilm WHERE r.user.id= :userId")

    @Query("SELECT f FROM Film f JOIN f.rates r WHERE r.user.id= :userId")
    List<Film> getAllFilmsFromUser(Long id);
}
