package com.epf.back_end.controllers;

import com.epf.back_end.dao.RateDao;
import com.epf.back_end.dto.request.RateDTORequest;
import com.epf.back_end.dto.request.UserDTORequest;
import com.epf.back_end.dto.response.RateDTO;
import com.epf.back_end.dto.response.UserDTO;
import com.epf.back_end.exceptions.ResourceNotFoundException;
import com.epf.back_end.interfaces.impl.RateServiceImpl;
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
@RequestMapping("/api/rates")
@AllArgsConstructor
public class RateController {

    private RateDao rateDao;
    private final RateServiceImpl rateService;


    /*@GetMapping("/bestNotes")
    public List<Rate> getBestRates(){
        return (List<Rate>) rateDao.getBestRates();
    }*/


    @PostMapping
    void addRate(@RequestBody Rate rate){
        rateDao.save(rate);
    }



    @GetMapping("/list")
    public ResponseEntity<List<RateDTO>> getAllRates() {
        try {
            List<RateDTO> rateDTOs = rateService.getAllRates();
            return new ResponseEntity<>(rateDTOs, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{idUser}/{idFilm}")
    public ResponseEntity<RateDTO> createRate(@PathVariable(value = "idUser") Long idUser,@PathVariable(value = "idFilm") Long idFilm,@RequestBody RateDTORequest rateDTORequest) {
        try {
            RateDTO createdRate = rateService.createRate(idUser,idFilm,rateDTORequest);
            return new ResponseEntity<>(createdRate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<RateDTO> getRateById(@PathVariable Long id) {
        try {
            RateDTO rateDTO = rateService.getRateById(id);
            return new ResponseEntity<>(rateDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
    public Map<String, Boolean> deleteRate(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Rate rate = rateDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rate not found for this id :: " + id));

        rateDao.delete(rate);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
    
}
