package com.epf.back_end.controllers;

import com.epf.back_end.dao.FilmDao;
import com.epf.back_end.exceptions.ResourceNotFoundException;
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
@CrossOrigin
@RequestMapping("/api/films")
@AllArgsConstructor
public class FilmController {
    
    private final FilmDao filmDao;

    @GetMapping("/list")
    public List<Film> getFilms(){
        return filmDao.findAll();
    }

    @PostMapping
    void addFilm(@RequestBody Film Film){
        filmDao.save(Film);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Film film = filmDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film not found for this id :: " + id));
        return ResponseEntity.ok().body(film);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable(value = "id") Long id,
                                           @RequestBody Film film) throws ResourceNotFoundException {
        Film existFilm = filmDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film not found for this id :: " + id));

        existFilm.setAuthor(film.getAuthor());
        existFilm.setTitle(film.getTitle());
        existFilm.setTime(film.getTime());
        final Film updatedFilm = filmDao.save(existFilm);
        return ResponseEntity.ok(updatedFilm);
    }

    @DeleteMapping("/film/{id}")
    public Map<String, Boolean> deleteFilm(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Film film = filmDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film not found for this id :: " + id));

        filmDao.delete(film);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
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
