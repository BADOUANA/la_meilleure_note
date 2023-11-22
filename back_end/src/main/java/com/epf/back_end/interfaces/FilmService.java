package com.epf.back_end.interfaces;

import com.epf.back_end.dto.FilmDTO;

import java.util.List;

public interface FilmService {
    FilmDTO getFilmById(Long FilmId);

    List<FilmDTO> getAllFilms();

    FilmDTO createFilm(FilmDTO FilmDTO);

    FilmDTO updateFilm(Long FilmId, FilmDTO FilmDTO);

    void deleteFilm(Long FilmId);
}
