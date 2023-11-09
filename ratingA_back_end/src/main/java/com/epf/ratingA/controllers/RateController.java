package com.epf.ratingA.controllers;

import com.epf.ratingA.dto.RateDto;
import com.epf.ratingA.models.Rate;
import com.epf.ratingA.services.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("rates")
@RestController
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    @GetMapping("/{id}")
    public ResponseEntity<Rate> getRateById(@PathVariable Long id) {
        Rate rate = rateService.findById(id);
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRate(@PathVariable Long id) {
        rateService.deleteRate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Void> addRate(
            @RequestBody RateDto rateDto,
            @RequestParam Long filmId,
            @RequestParam Long userId
    ) {
        rateService.addRate(rateDto, filmId, userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}