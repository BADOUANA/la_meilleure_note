package com.epf.back_end.interfaces.impl;

import com.epf.back_end.dao.FilmDao;
import com.epf.back_end.dao.RateDao;
import com.epf.back_end.dao.UserDao;
import com.epf.back_end.dto.request.RateDTORequest;
import com.epf.back_end.dto.response.RateDTO;
import com.epf.back_end.dto.response.UserDTO;
import com.epf.back_end.exceptions.ResourceNotFoundException;
import com.epf.back_end.interfaces.RateService;
import com.epf.back_end.mappers.RateMapper;
import com.epf.back_end.models.Film;
import com.epf.back_end.models.Rate;
import com.epf.back_end.models.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {
    private final RateDao rateDao;
    private final FilmDao filmDao;
    private final UserDao userDao;

    private final RateMapper rateMapper;


    @Override
    public RateDTO getRateById(Long id) throws ResourceNotFoundException {
        Rate rate = rateDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rate not found with id: " + id));
        return rateMapper.rateToRateDTO(rate);
    }

    @Override
    public List<RateDTO> getAllRates() throws ResourceNotFoundException{
        List<Rate> rates = rateDao.findAll();
        List<RateDTO> dtoResponses = new ArrayList<>();

        for (Rate rate : rates) {
            RateDTO rateDTO = rateMapper.rateToRateDTO(rate);
            dtoResponses.add(rateDTO);
        }
        return dtoResponses;
    }

    @Override
    public RateDTO createRate(Long userId, Long filmId, RateDTORequest rateDTORequest) throws RuntimeException{
        // Find Film and User by their IDs
        Optional<Film> film = filmDao.findById(filmId);
        Optional<User> user = userDao.findById(userId);
        try {
            if (user.isPresent()){
                // Set User ID in RateDTORequest
                rateDTORequest.setIdUser(userId);
                if (film.isPresent()){
                    // Set Film ID in RateDTORequest
                    rateDTORequest.setIdFilm(filmId);
                    try {
                        Rate rate = rateMapper.rateDTOResquestToRate(rateDTORequest);
                        Rate savedRate = rateDao.save(rate);
                        return rateMapper.rateToRateDTO(savedRate);
                    }catch (Exception e){
                        throw new RuntimeException();
                    }

                }else {
                    throw new ResourceNotFoundException("Verify if film exist ");
                }
            }else {
                throw new ResourceNotFoundException("Verify if user exist ");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    @Override
    public RateDTO updateRate(Long userId, Long id, RateDTORequest rateDTORequest) throws ResourceNotFoundException{

        Rate existingRate = rateDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Check if the user is authorized to update this rate
        if (existingRate.getUser().getId() == userId){
            rateMapper.updateRateFromDTO(rateDTORequest, existingRate);
            Rate updatedRate = rateDao.save(existingRate);
            return rateMapper.rateToRateDTO(updatedRate);
        }else {
            throw new ResourceNotFoundException("You are not authorized to update this rate");
        }



    }

    @Override
    public void deleteRate(Long userId, Long id) throws ResourceNotFoundException{
        Rate existingRate = rateDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Check if the user is authorized to delete this rate and delete it
        if (existingRate.getUser().getId() == userId){
            rateDao.deleteById(id);
        }else {
            throw new ResourceNotFoundException("You are not authorized to delete this rate");
        }

    }


    @Override
    public List<RateDTO> getRatesByUserId(Long id) throws ResourceNotFoundException {
        List<Rate> rates = rateDao.getAllRatesFromUser(id);
        List<RateDTO> dtoResponses = new ArrayList<>();

        // Convert each Rate entity to RateDTO using the mapper and collect in a list
        for (Rate rate : rates) {
            RateDTO rateDTO = rateMapper.rateToRateDTO(rate);
            dtoResponses.add(rateDTO);
        }
        return dtoResponses;
    }

    @Override
    public List<RateDTO> getRatesByFilmId(Long id) throws ResourceNotFoundException {
        List<Rate> rates = rateDao.getAllRatesFromFilm(id);
        List<RateDTO> dtoResponses = new ArrayList<>();

        for (Rate rate : rates) {
            RateDTO rateDTO = rateMapper.rateToRateDTO(rate);
            dtoResponses.add(rateDTO);
        }
        return dtoResponses;
    }
}
