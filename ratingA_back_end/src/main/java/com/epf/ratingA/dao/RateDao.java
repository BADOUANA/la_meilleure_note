package com.epf.ratingA.dao;

import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.Rate;
import com.epf.ratingA.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateDao extends JpaRepository<Rate, Long> {
    List<Rate> findByUserId(Long user_id);
    List<Rate> findByFilmIdFilm(Long film_id);
    @Query("SELECT r.film FROM Rate r WHERE r.user.id = :user_id")
    List<Film> findFilmsByUserId(@Param("user_id") Long user_id);
    @Query("SELECT r.film FROM Rate r WHERE r.note = (SELECT MAX(r2.note) FROM Rate r2)")
    List<Film> findFilmsWithHighestNote();

    @Query("SELECT DISTINCT r.user FROM Rate r WHERE r.film.idFilm = :film_id")
    List<User> findUsersByFilm(@Param("film_id") Long film_id);





}
