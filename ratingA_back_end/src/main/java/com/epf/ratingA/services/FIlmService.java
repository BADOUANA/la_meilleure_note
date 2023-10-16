package com.epf.ratingA.services;

import com.epf.ratingA.dao.FilmDao;
import com.epf.ratingA.models.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FIlmService {
    private final FilmDao filmDao;

    public List<Film> findAll(){return filmDao.findAll();}
    public List<Film> findAllBestRates(){return filmDao.getBestFilmByRates();}

}
