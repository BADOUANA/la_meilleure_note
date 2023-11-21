package com.epf.back_end.controllers;

import com.epf.back_end.dao.RateDao;
import com.epf.back_end.exceptions.ResourceNotFoundException;
import com.epf.back_end.models.Rate;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/rates")
@AllArgsConstructor
public class RateController {
    private RateDao rateDao;


    @GetMapping("/list")
    public List<Rate> getRates(){
        return (List<Rate>) rateDao.findAll();
    }

    @GetMapping("/bestNotes")
    public List<Rate> getBestRates(){
        return (List<Rate>) rateDao.getBestRates();
    }


    @PostMapping
    void addRate(@RequestBody Rate rate){
        rateDao.save(rate);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Rate> getRateById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Rate rate = rateDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rate not found for this id :: " + id));
        return ResponseEntity.ok().body(rate);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Rate> updateRate(@PathVariable(value = "id") Long id,
                                           @RequestBody Rate rate) throws ResourceNotFoundException {
        Rate existRate = rateDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rate not found for this id :: " + id));

        existRate.setNote(rate.getNote());
        existRate.setSummary(rate.getSummary());
        existRate.setDetailSummary(rate.getDetailSummary());
        final Rate updatedRate = rateDao.save(existRate);
        return ResponseEntity.ok(updatedRate);
    }

    @DeleteMapping("/rate/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Rate rate = rateDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rate not found for this id :: " + id));

        rateDao.delete(rate);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
    
}
