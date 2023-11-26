package com.epf.back_end.dao;

import com.epf.back_end.models.Film;
import com.epf.back_end.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmDao extends JpaRepository<Film, Long> {
    @Query("SELECT r FROM Rate r WHERE r.film.id= :film_id")
    List<Rate> getAllRatesFromFilm(@Param("film_id")Long filmId);

    @Query("SELECT f FROM Film f WHERE LOWER(f.title) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Film> searchByTitle(@Param("searchTerm") String searchTerm);

    @Query("SELECT f, SUM(r.note) as totalRate, COUNT(r) as totalRatings FROM Film f LEFT JOIN Rate r ON r.film.id = f.id WHERE f.id = :film_id GROUP BY f")
    Film getFilmTotalRate(@Param("film_id") Long filmId);

    @Query("SELECT AVG(r.note) FROM Rate r WHERE r.film.id = :film_id")
    Double getAverageRatingForFilm(@Param("film_id") Long filmId);

    @Query("SELECT f, AVG(r.note) as averageRate FROM Film f " +
            "LEFT JOIN Rate r ON r.film.id = f.id " +
            "GROUP BY f " +
            "ORDER BY averageRate DESC")
    List<Film> getFilmsOrderByAverageRate();

    @Query("SELECT f FROM Film f LEFT JOIN Rate r ON r.film.id = f.id WHERE r.note >= 8")
    List<Film> getBestFilmByRates();

}
