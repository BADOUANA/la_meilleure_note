package com.epf.ratingA.services;

import com.epf.ratingA.dao.RateDao;
import com.epf.ratingA.models.Rate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RateService {
    private final RateDao rateDao;

    public Rate findById(Long id){return rateDao.findById(id).get();}
}
