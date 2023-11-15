package com.epf.ratingA.services;

import com.epf.ratingA.dao.FilmDao;
import com.epf.ratingA.dao.RateDao;
import com.epf.ratingA.dao.UserDao;
import com.epf.ratingA.dto.RateDto;
import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.Rate;
import com.epf.ratingA.models.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceRate {
    private final RateDao rateDao;
    private final FilmDao filmDao;
    private final UserDao userDao;

    public Rate findById(Long id){return rateDao.findById(id).get();}

    public Long addRate(RateDto rateDto, Long filmId, Long userId){
        Optional<Film> film = filmDao.findById(filmId);
        Optional<User> user = userDao.findById(userId);
        try {
            if ( user.isPresent()){
                if (film.isPresent()){
                    Rate rate = Rate.builder()
                            .film(film.get())
                            .user(user.get())
                            .note(rateDto.getNote())
                            .summary(rateDto.getSummary())
                            .detailSummary(rateDto.getDetailSummary())
                            .build();
                    rateDao.save(rate);
                }else {
                    throw new EntityNotFoundException("Verify if film exist ");
                }
            }else {
                throw new EntityNotFoundException("Verify if user exist ");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return rateDto.getIdRate();
    }

    public void updateRate(RateDto rateDto, Long rateId, Long filmId, Long userId){
        Optional<Rate> findrate = rateDao.findById(rateId);
        Optional<Film> film = filmDao.findById(filmId);
        Optional<User> user = userDao.findById(userId);
        try {
            if (film.isPresent() && user.isPresent() && findrate.isPresent() ){
                Rate rate = Rate.builder()
                        .film(film.get())
                        .user(user.get())
                        .note(rateDto.getNote())
                        .summary(rateDto.getSummary())
                        .detailSummary(rateDto.getDetailSummary())
                        .build();
                rateDao.save(rate);
            }else {
                throw new EntityNotFoundException("Verify if user, film, rate exist ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteRate(Long id){rateDao.deleteById(id);}

}
