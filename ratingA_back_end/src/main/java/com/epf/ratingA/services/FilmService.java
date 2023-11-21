package com.epf.ratingA.services;

import com.epf.ratingA.dao.FilmDao;
import com.epf.ratingA.dao.UserDao;
import com.epf.ratingA.dto.FilmDto;
import com.epf.ratingA.exceptions.FilmException;
import com.epf.ratingA.mappers.FilmMapper;
import com.epf.ratingA.mappers.UserMapper;
import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.Image;
import com.epf.ratingA.models.Rate;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FilmService  {
    private final FilmDao filmDao;
    private final FilmMapper filmMapper;
    @Autowired
    public FilmService(FilmDao filmDao, FilmMapper filmMapper) {
        this.filmDao = filmDao;
        this.filmMapper = filmMapper;
    }

    public List<Film> findAll(){return filmDao.findAll();}
    public List<Film> findAllBestFilmByRates(){return filmDao.getBestFilmByRates();}
    public List<Rate> findRatesByFilmId(Long id){return filmDao.getAllRatesFromFilm(id);}
    public Film findFilmByTitle(String title){return filmDao.findFilmByTitle(title);}

    /*@Override
    public FilmDto createFilm(FilmDto filmDto) throws FilmException {
        try {
                Film film = Film.builder()
                        .idFilm(filmDto.getIdFilm())
                        .title(filmDto.getTitle())
                        .author(filmDto.getAuthor())
                        .outDate(filmDto.getOutDate())
                        .time(filmDto.getTime())
                        .category(filmDto.getCategories())
                        .build();
                return Stream.of(filmDao.save(film)).map(filmMapper::filmToFilmDto).findFirst().get();
        }catch (Exception e){
            e.printStackTrace();
            try {
                throw new FilmException();
            } catch (FilmException ex) {
                throw new RuntimeException(ex);
            }
        }
    }*/
    public FilmDto createFilm(FilmDto filmDto) throws FilmException {
        try {

            Film film = filmMapper.filmDtoToFilm(filmDto);
            Film savedFilm = filmDao.save(film);
            return filmMapper.filmToFilmDto(savedFilm);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FilmException();
        }
    }
    public void updateFilm(FilmDto filmDto, Long filmId) throws FilmException{
        try {
            Film existingFilm = filmDao.findById(filmId)
                    .orElseThrow(() -> new NoSuchElementException("Film doesn't exist"));

            Film updatedFilm = filmMapper.updateFilmFromDto(filmDto, existingFilm);
            filmDao.save(updatedFilm);
        }catch (Exception e){
            e.printStackTrace();
            throw new FilmException();
        }
    }

    public void deleteFilm(Long id){filmDao.deleteById(id);}


    public List<Film> searchFilmsByTitle(String searchTerm) {
        return filmDao.searchByTitle(searchTerm);
    }

}
