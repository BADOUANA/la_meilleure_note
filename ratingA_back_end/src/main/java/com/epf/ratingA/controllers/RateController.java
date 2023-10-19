package com.epf.ratingA.controllers;

import com.epf.ratingA.dto.RateDto;
import com.epf.ratingA.models.Rate;
import com.epf.ratingA.services.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("rates")
@RestController
@RequiredArgsConstructor
public class RateController {
    private final RateService rateService;

    @GetMapping("/{id}")
    public Rate getRateById(@PathVariable Long id){return rateService.findById(id);}

    @DeleteMapping("/{id}")
    public void deleteRate(@PathVariable Long id){rateService.deleteRate(id);}

    @PostMapping("")
    public void addRate(@RequestBody RateDto rateDto, @RequestParam Long filmId, @RequestParam Long userId){rateService.addRate(rateDto, filmId, userId );}
}
