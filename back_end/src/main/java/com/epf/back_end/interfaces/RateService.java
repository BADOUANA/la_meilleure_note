package com.epf.back_end.interfaces;

import com.epf.back_end.dto.RateDTO;

import java.util.List;

public interface RateService {
    RateDTO getRateById(Long rateId);

    List<RateDTO> getAllRates();

    Long createRate(RateDTO rateDTO,Long filmId, Long userId);

    RateDTO updateRate(Long rateId, RateDTO rateDTO);

    void deleteRate(Long rateId);

    List<RateDTO> getBestRates();

}
