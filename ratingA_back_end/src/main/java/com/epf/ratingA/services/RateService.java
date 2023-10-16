package com.epf.ratingA.services;

import com.epf.ratingA.dao.RateDao;
import com.epf.ratingA.dto.RateDto;
import com.epf.ratingA.mappers.RateMapper;
import com.epf.ratingA.models.Rate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class RateService {
    private final RateDao rateDao;

    public Rate findById(Long id){return rateDao.findById(id).get();}

    public Long createRate(RateDto rateDto){
        try {
            Rate rate = RateMapper.INSTANCE.fromRateDto(rateDto, null);
            rateDao.save(rate);
            return rate.getIdRate();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void updateRate(RateDto rateDto, Long rateId){
        rateDao.findById(rateId).orElseThrow(()->new NoSuchElementException("Film doesn't exist"));
        try {
            Rate rate = RateMapper.INSTANCE.fromRateDto(rateDto, rateId);
            rateDao.save(rate);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteRate(Long id){rateDao.deleteById(id);}

}
