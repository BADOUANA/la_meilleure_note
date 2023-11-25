package com.epf.back_end.interfaces;

import com.epf.back_end.dto.request.RateDTORequest;
import com.epf.back_end.dto.response.RateDTO;
import com.epf.back_end.exceptions.ResourceNotFoundException;

import java.util.List;

public interface RateService {
    RateDTO getRateById(Long id)throws ResourceNotFoundException;

    List<RateDTO> getAllRates()throws ResourceNotFoundException;

    RateDTO createRate(Long userId, Long filmId,RateDTORequest rateDTORequest )throws RuntimeException;

    RateDTO updateRate(Long rateId, RateDTORequest rateDTORequest)throws ResourceNotFoundException;

    void deleteRate(Long id);

    /*List<RateDTO> getBestRates();*/

}
