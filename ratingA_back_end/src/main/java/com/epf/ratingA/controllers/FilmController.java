package com.epf.ratingA.controllers;

import com.epf.ratingA.dto.FilmDto;
import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.Rate;
import com.epf.ratingA.services.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("localhost:8081")
@RequestMapping("/films")
@RestController
@RequiredArgsConstructor
public class FilmController {
    private FilmService filmService;
    @GetMapping
    public ResponseEntity<List<Film>> getAllFilms() {
        List<Film> films = filmService.findAll();
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @GetMapping("/best")
    public ResponseEntity<List<Film>> getBestFilms() {
        List<Film> bestFilms = filmService.findAllBestFilmByRates();
        return new ResponseEntity<>(bestFilms, HttpStatus.OK);
    }

    @GetMapping("/{id}/rates")
    public ResponseEntity<List<Rate>> findRatesByFilmId(@PathVariable Long id) {
        List<Rate> rates = filmService.findRatesByFilmId(id);
        return new ResponseEntity<>(rates, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addFilm(@RequestBody FilmDto filmDto) throws Exception {
        filmService.createFilm(filmDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFilm(@RequestBody FilmDto filmDto, @PathVariable Long id) {
        filmService.updateFilm(filmDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Film>> searchFilms(@RequestParam(name = "title") String nom) {
        List<Film> films = filmService.searchFilmsByTitle(nom);
        return new ResponseEntity<>(films, HttpStatus.OK);
    }
}
