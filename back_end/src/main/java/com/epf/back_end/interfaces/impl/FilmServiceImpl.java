package com.epf.back_end.interfaces.impl;

import com.epf.back_end.dao.FilmDao;
import com.epf.back_end.dto.request.FilmDTORequest;
import com.epf.back_end.dto.response.FilmDTO;
import com.epf.back_end.dto.response.UserDTO;
import com.epf.back_end.enumer.Role;
import com.epf.back_end.exceptions.ResourceNotFoundException;
import com.epf.back_end.interfaces.FilmService;
import com.epf.back_end.interfaces.ImageService;
import com.epf.back_end.interfaces.UserService;
import com.epf.back_end.mappers.FilmMapper;
import com.epf.back_end.models.Film;
import com.epf.back_end.models.Image;
import com.epf.back_end.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmDao filmDao;
    private final FilmMapper filmMapper;
    private final ImageService imageService;
    private final UserService userService;

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

    @Override
    public Double getAverageRatingForFilm(Long id) {
        Optional<Film> film = filmDao.findById(id);
        if (film.isPresent()) {
            return filmDao.getAverageRatingForFilm(id);
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public List<FilmDTO> getFilmsOrderByAverageRate() throws ResourceNotFoundException {
        List<Film> films = filmDao.getFilmsOrderByAverageRate();
        List<FilmDTO> dtoResponses = new ArrayList<>();

        for (Film film : films) {
            FilmDTO filmDTO = filmMapper.filmToFilmDTO(film);
            dtoResponses.add(filmDTO);
        }
        return dtoResponses;
    }

    @Override
    public void updateFilmImage(Long userId, Long id, MultipartFile image) throws IOException, ResourceNotFoundException {
        Film existingFilm = filmDao.findById(id).orElseThrow(()->new RuntimeException("User not found"+id));
        Image existingImage = existingFilm.getImage();
        UserDTO userDTO = userService.getUserById(userId);
        if (existingImage != null && userDTO.getRole().equals(Role.ADMIN)){
            imageService.updateImage(existingImage.getId(),image);
        }else {
            throw new RuntimeException();
        }

    }
}
