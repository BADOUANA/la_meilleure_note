package com.epf.ratingA.services;

import com.epf.ratingA.dao.FilmDao;
import com.epf.ratingA.dto.FilmDto;
import com.epf.ratingA.exceptions.FilmException;
import com.epf.ratingA.interfaces.IFilmService;
import com.epf.ratingA.mappers.FilmMapper;
import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.Image;
import com.epf.ratingA.models.Rate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class FilmService implements IFilmService {
    private final FilmDao filmDao;
    private final FilmMapper filmMapper;

    @Override
    public List<Film> findAll(){return filmDao.findAll();}
    @Override
    public List<Film> findAllBestFilmByRates(){return filmDao.getBestFilmByRates();}
    @Override
    public List<Rate> findRatesByFilmId(Long id){return filmDao.getAllRatesFromFilm(id);}
    @Override
    public Film findFilmByTitle(String title){return filmDao.findFilmByTitle(title);}

    @Override
    public FilmDto createFilm(FilmDto filmDto) throws FilmException {
        try {
           /*Film film = filmMapper.filmDtoToFilm(filmDto);
            Film filmCreated = filmDao.save(film);
            return filmCreated.getIdFilm();*/
                Film film = Film.builder()
                        .idFilm(filmDto.getIdFilm())
                        .title(filmDto.getTitle())
                        .author(filmDto.getAuthor())
                        .outDate(filmDto.getOutDate())

                        .time(filmDto.getTime())
                        .category(filmDto.getCategories())
                        .build();
                return Stream.of(filmDao.save(film)).map(filmMapper::filmToFilmDto).findFirst().get();
        }catch (Exception e){
            e.printStackTrace();
            try {
                throw new FilmException();
            } catch (FilmException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    @Override
    public void updateFilm(FilmDto filmDto, Long filmId){
        filmDao.findById(filmId).orElseThrow(()->new NoSuchElementException("Film doesn't exist"));
        try {
          /*  Film film = FilmMapper.INSTANCE.filmDtoToFilm(filmDto);
            filmDao.save(film);*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFilm(Long id){filmDao.deleteById(id);}


    @Override
    public List<Film> searchFilmsByTitle(String searchTerm) {
        return filmDao.searchByTitle(searchTerm);
    }

}
