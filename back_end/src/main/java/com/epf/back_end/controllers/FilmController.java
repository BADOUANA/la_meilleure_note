package com.epf.back_end.controllers;

import com.epf.back_end.dao.FilmDao;
import com.epf.back_end.dto.request.FilmDTORequest;
import com.epf.back_end.dto.request.UserDTORequest;
import com.epf.back_end.dto.response.FilmDTO;
import com.epf.back_end.dto.response.UserDTO;
import com.epf.back_end.exceptions.ResourceNotFoundException;
import com.epf.back_end.interfaces.FilmService;
import com.epf.back_end.interfaces.impl.FilmServiceImpl;
import com.epf.back_end.models.Film;
import com.epf.back_end.models.Rate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/films")
@AllArgsConstructor
public class FilmController {
    
    private final FilmDao filmDao;
    private final FilmService filmService;

    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAllFilms() {
        try {
            List<FilmDTO> filmDTOs = filmService.getAllFilms();
            return new ResponseEntity<>(filmDTOs, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/best")
    public ResponseEntity<List<FilmDTO>> getFilmsOrderByAverageRate() {
        try {
            List<FilmDTO> filmDTOs = filmService.getFilmsOrderByAverageRate();
            return new ResponseEntity<>(filmDTOs, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<FilmDTO> createFilm(@RequestBody FilmDTORequest filmDTORequest) {
        try {
            FilmDTO createdFilm = filmService.createFilm(filmDTORequest);
            return new ResponseEntity<>(createdFilm, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getFilmById(@PathVariable(value = "id") Long id){
        try {
            FilmDTO filmDTO = filmService.getFilmById(id);
            return new ResponseEntity<>(filmDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}/avgNote")
    public ResponseEntity<Double> getAverageRatingForFilm(@PathVariable(value = "id") Long id){
        try {
            Double noteMoy = filmService.getAverageRatingForFilm(id);
            return new ResponseEntity<>(noteMoy, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<FilmDTO> updateFilm(@PathVariable(value = "id") Long id, @RequestBody FilmDTORequest filmDTORequest) {
        try {
            FilmDTO updatedFilm = filmService.updateFilm(id, filmDTORequest);
            return new ResponseEntity<>(updatedFilm, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{id}/myRates")
    public ResponseEntity<List<Rate>> getRatesByFilmId(@PathVariable Long id){
        List<Rate> rates = filmDao.getAllRatesFromFilm(id);
        return ResponseEntity.ok().body(rates);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Film>> searchFilms(@RequestParam(name = "title") String title) {
        List<Film> films = filmDao.searchByTitle(title);
        return new ResponseEntity<>(films, HttpStatus.OK);
    }
}
