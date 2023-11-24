package com.epf.back_end.interfaces.impl;

import com.epf.back_end.dao.FilmDao;
import com.epf.back_end.dao.RateDao;
import com.epf.back_end.dao.UserDao;
import com.epf.back_end.dto.RateDTO;
import com.epf.back_end.interfaces.RateService;
import com.epf.back_end.mappers.RateMapper;
import com.epf.back_end.models.Film;
import com.epf.back_end.models.Rate;
import com.epf.back_end.models.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public RateDTO getRateById(Long id) {
        Rate rate = rateDao.findById(id).orElse(null);
        return (rate != null) ? rateMapper.rateToRateDTO(rate) : null;
    }

    @Override
    public List<RateDTO> getAllRates() {
        List<Rate> rates = rateDao.findAll();
        return rateMapper.ratesToRateDTOs(rates);
    }

    @Override
    public Long createRate(RateDTO rateDTO,Long filmId, Long userId) {
        Optional<Film> film = filmDao.findById(filmId);
        Optional<User> user = userDao.findById(userId);
        try {
            if ( user.isPresent()){
                if (film.isPresent()){
                    Rate rate = Rate.builder()
                            .film(film.get())
                            .user(user.get())
                            .note(rateDTO.getNote())
                            .summary(rateDTO.getSummary())
                            .detailSummary(rateDTO.getDetailSummary())
                            .build();
                    rateDao.save(rate);
                    return rateDTO.getId();

                }else {
                    throw new EntityNotFoundException("Verify if film exist ");
                }
            }else {
                throw new EntityNotFoundException("Verify if user exist ");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    @Override
    public RateDTO updateRate(Long id, RateDTO rateDTO) {
        if (rateDao.existsById(id)) {
            Rate rate = rateMapper.rateDTOToRate(rateDTO);
            rate.setId(id);
            Rate updatedRate = rateDao.save(rate);
            return rateMapper.rateToRateDTO(updatedRate);
        } else {
            return null; // Handle the case where the rate with the given id doesn't exist
        }
    }

    @Override
    public void deleteRate(Long id) {
        rateDao.deleteById(id);
    }

    @Override
    public List<RateDTO> getBestRates() {
        List<Rate> bestRates = rateDao.getBestRates();
        return rateMapper.ratesToRateDTOs(bestRates);
    }
}
