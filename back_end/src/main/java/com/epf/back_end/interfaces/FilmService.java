package com.epf.back_end.interfaces;

import com.epf.back_end.dto.request.FilmDTORequest;
import com.epf.back_end.dto.response.FilmDTO;
import com.epf.back_end.exceptions.ResourceNotFoundException;

import java.util.List;

public interface FilmService {
    FilmDTO getFilmById(Long id)throws ResourceNotFoundException;

    List<FilmDTO> getAllFilms() throws ResourceNotFoundException;

    FilmDTO createFilm(FilmDTORequest filmDTORequest) throws RuntimeException;

    FilmDTO updateFilm(Long id, FilmDTORequest filmDTORequest)throws ResourceNotFoundException;

    void deleteFilm(Long id);
}
