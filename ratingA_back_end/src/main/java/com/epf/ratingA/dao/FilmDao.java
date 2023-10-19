package com.epf.ratingA.dao;

import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmDao extends JpaRepository<Film,Long> {
    @Query("SELECT f.rates FROM Film f WHERE f.idFilm= :filmId")
    List<Rate> getAllRatesFromFilm(Long filmId);

    @Query("SELECT f FROM Film f JOIN f.rates r WHERE r.note >= 0.8")
    List<Film> getBestFilmByRates();

}
