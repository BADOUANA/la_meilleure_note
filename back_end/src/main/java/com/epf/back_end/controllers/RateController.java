package com.epf.back_end.controllers;

import com.epf.back_end.dao.RateDao;
import com.epf.back_end.dto.request.RateDTORequest;
import com.epf.back_end.dto.request.UserDTORequest;
import com.epf.back_end.dto.response.RateDTO;
import com.epf.back_end.dto.response.UserDTO;
import com.epf.back_end.exceptions.ResourceNotFoundException;
import com.epf.back_end.interfaces.RateService;
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

    private final RateService rateService;

    @GetMapping
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


    @PutMapping("/{idUser}/{id}")
    public ResponseEntity<RateDTO> updateRate(@PathVariable(value = "idUser") Long idUser,
                                           @PathVariable(value = "id") Long id,
                                           @RequestBody RateDTORequest rateDTORequest) throws ResourceNotFoundException {
        RateDTO updatedRate = rateService.updateRate(idUser, id, rateDTORequest);
        return ResponseEntity.ok(updatedRate);
    }

    @DeleteMapping("/{idUser}/{id}")
    public ResponseEntity<String> deleteRate(@PathVariable(value = "idUser") Long idUser,@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        rateService.deleteRate(idUser, id);
        return ResponseEntity.ok("Rate deleted successfully");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<RateDTO>> getRatesByUserId(@PathVariable Long id) throws ResourceNotFoundException {
        List<RateDTO> rates = rateService.getRatesByUserId(id);
        return ResponseEntity.ok(rates);
    }

    @GetMapping("/film/{id}")
    public ResponseEntity<List<RateDTO>> getRatesByFilmId(@PathVariable Long id) throws ResourceNotFoundException {
        List<RateDTO> rates = rateService.getRatesByFilmId(id);
        return ResponseEntity.ok(rates);
    }
    
}
