package com.epf.ratingA.controllers;

import com.epf.ratingA.dto.FilmDto;
import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.Rate;
import com.epf.ratingA.services.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("films")
@RestController
@RequiredArgsConstructor
public class FilmController {
    private FilmService filmService;

    @GetMapping("")
    public List<Film> getAllFilms(){return filmService.findAll();}

    @GetMapping("/bestFilms")
    public List<Film> getBestFilm(){return filmService.findAllBestFilmByRates();}

    @GetMapping("/{id}/ratesFilm")
    public List<Rate> findRatesByFilmId(Long filmId){return filmService.findRatesByFilmId(filmId);}

    @PostMapping("")
    public void addFilm(@RequestBody FilmDto filmDto){}

    @PutMapping("/{id}")
    public void updateFilm(@RequestBody FilmDto filmDto, @PathVariable Long id){filmService.updateFilm(filmDto,id);}

    @DeleteMapping("/{id}")
    public void deleteFilm(@PathVariable Long filmId){}

    //@PostMapping()


}
