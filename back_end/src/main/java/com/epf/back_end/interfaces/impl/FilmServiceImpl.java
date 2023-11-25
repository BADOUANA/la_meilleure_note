package com.epf.back_end.interfaces.impl;

import com.epf.back_end.dao.FilmDao;
import com.epf.back_end.dto.request.FilmDTORequest;
import com.epf.back_end.dto.response.FilmDTO;
import com.epf.back_end.exceptions.ResourceNotFoundException;
import com.epf.back_end.interfaces.FilmService;
import com.epf.back_end.mappers.FilmMapper;
import com.epf.back_end.models.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmDao filmDao;
    private final FilmMapper filmMapper;

    @Override
    public FilmDTO getFilmById(Long id) throws ResourceNotFoundException {
        Film film = filmDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Film not found with id: " + id));
        return filmMapper.filmToFilmDTO(film);
    }

    @Override
    public List<FilmDTO> getAllFilms() throws ResourceNotFoundException {
        List<Film> films = filmDao.findAll();
        List<FilmDTO> dtoResponses = new ArrayList<>();

        for (Film film : films) {
            FilmDTO filmDTO = filmMapper.filmToFilmDTO(film);
            dtoResponses.add(filmDTO);
        }
        return dtoResponses;
    }

    @Override
    public FilmDTO createFilm(FilmDTORequest filmDTORequest) throws RuntimeException {
        try {
            Film film = filmMapper.filmDTOToFilm(filmDTORequest);
            Film savedFilm = filmDao.save(film);
            return filmMapper.filmToFilmDTO(savedFilm);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public FilmDTO updateFilm(Long id, FilmDTORequest filmDTORequest)throws ResourceNotFoundException {
        Film existingFilm = filmDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film not found with id: " + id));
        filmMapper.updateFilmFromDTO(filmDTORequest, existingFilm);
        Film updatedFilm = filmDao.save(existingFilm);
        return filmMapper.filmToFilmDTO(updatedFilm);
    }

    @Override
    public void deleteFilm(Long id) {
            filmDao.deleteById(id);
    }
}
