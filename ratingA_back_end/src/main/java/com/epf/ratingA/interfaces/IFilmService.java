package com.epf.ratingA.interfaces;

import com.epf.ratingA.dto.FilmDto;
import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.Rate;

import java.util.List;

public interface IFilmService {
    List<Film> findAll();

    List<Film> findAllBestFilmByRates();

    List<Rate> findRatesByFilmId(Long id);

    Film findFilmByTitle(String title);

    FilmDto createFilm(FilmDto filmDto) throws Exception;

    void updateFilm(FilmDto filmDto, Long filmId);

    void deleteFilm(Long id);

    List<Film> searchFilmsByTitle(String searchTerm);
}
