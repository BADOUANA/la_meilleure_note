package com.epf.ratingA.services;

import com.epf.ratingA.dao.FilmDao;
import com.epf.ratingA.dto.FilmDto;
import com.epf.ratingA.mappers.FilmMapper;
import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.Rate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmDao filmDao;

    public List<Film> findAll(){return filmDao.findAll();}
    public List<Film> findAllBestFilmByRates(){return filmDao.getBestFilmByRates();}
    public List<Rate> findRatesByFilmId(Long id){return filmDao.getAllRatesFromFilm(id);}

    public Long createFilm(FilmDto filmDto){
        try {
            Film film = FilmMapper.INSTANCE.fromFilmDto(filmDto, null);
            filmDao.save(film);
            return film.getIdFilm();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void updateFilm(FilmDto filmDto, Long filmId){
        filmDao.findById(filmId).orElseThrow(()->new NoSuchElementException("Film doesn't exist"));
        try {
            Film film = FilmMapper.INSTANCE.fromFilmDto(filmDto, filmId);
            filmDao.save(film);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteFilm(Long id){filmDao.deleteById(id);}

}
