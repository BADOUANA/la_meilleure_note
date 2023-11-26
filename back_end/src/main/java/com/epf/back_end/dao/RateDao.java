package com.epf.back_end.dao;

import com.epf.back_end.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateDao extends JpaRepository<Rate,Long> {

    @Query("SELECT r FROM Rate r WHERE r.user.id= :user_id")
    List<Rate> getAllRatesFromUser(@Param("user_id")Long userId);

    @Query("SELECT r FROM Rate r WHERE r.film.id= :film_id")
    List<Rate> getAllRatesFromFilm(@Param("film_id")Long filmId);


}
