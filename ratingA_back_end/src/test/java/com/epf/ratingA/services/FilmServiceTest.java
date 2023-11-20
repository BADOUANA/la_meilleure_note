package com.epf.ratingA.services;

import com.epf.ratingA.config.TestConfig;
import com.epf.ratingA.dao.FilmDao;
import com.epf.ratingA.dto.FilmDto;
import com.epf.ratingA.enumer.Category;
import com.epf.ratingA.exceptions.FilmException;
import com.epf.ratingA.mappers.FilmMapper;
import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.Rate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class FilmServiceTest {

    @Mock
    private FilmDao filmDao;

    @Mock
    private FilmMapper filmMapper;
    @InjectMocks
    private FilmService filmService;


    @Test
    public void testFindAll() {
        // Configuration du comportement simulé du dao
        when(filmDao.findAll()).thenReturn(Collections.singletonList(new Film()));

        // Exécution de la méthode à tester
        List<Film> films = filmService.findAll();

        // Assertions
        assertNotNull(films);
        assertFalse(films.isEmpty());
    }

    @Test
    public void testFindAllBestFilmByRates() {
        // Configuration du comportement simulé du dao
        when(filmDao.getBestFilmByRates()).thenReturn(Collections.singletonList(new Film()));

        // Exécution de la méthode à tester
        List<Film> bestFilms = filmService.findAllBestFilmByRates();

        // Assertions
        assertNotNull(bestFilms);
        assertFalse(bestFilms.isEmpty());
    }

    @Test
    public void testFindRatesByFilmId() {
        // Configuration du comportement simulé du dao
        when(filmDao.getAllRatesFromFilm(anyLong())).thenReturn(Collections.singletonList(new Rate()));

        // Exécution de la méthode à tester
        List<Rate> rates = filmService.findRatesByFilmId(1L);

        // Assertions
        assertNotNull(rates);
        assertFalse(rates.isEmpty());
    }

    @Test
    public void testFindFilmByTitle() {
        // Configuration du comportement simulé du dao
        when(filmDao.findFilmByTitle(anyString())).thenReturn(new Film());

        // Exécution de la méthode à tester
        Film film = filmService.findFilmByTitle("Title");

        // Assertions
        assertNotNull(film);
    }


    @Test
    void testCreateFilm() throws FilmException {
        // Create a sample FilmDto
        FilmDto filmDto = FilmDto.builder()
                .idFilm(1L)
                .title("Test Film")
                .author("Test Author")
                .outDate("2023-11-13")
                .time(120)
                .categories(Arrays.asList(Category.ACTION, Category.DRAMA)) // Assuming you have a category converter
                .build();

        // Create a sample Film
        Film film = Film.builder()
                .idFilm(1L)
                .title("Test Film")
                .author("Test Author")
                .outDate("2023-11-13")
                .time(120)
                .category(Arrays.asList(Category.ACTION, Category.DRAMA)) // Assuming you have a category converter
                .build();

        // Configure the behavior of mock objects
        when(filmMapper.filmDtoToFilm(any())).thenReturn(film);
        when(filmDao.save(any())).thenReturn(film);
        when(filmMapper.filmToFilmDto(any())).thenReturn(filmDto);

        // Invoke the method to be tested
        FilmDto createdFilmDto = filmService.createFilm(filmDto);

        // Verify the results
        assertNotNull(createdFilmDto);
        assertEquals(filmDto.getIdFilm(), createdFilmDto.getIdFilm());

        // Verify the interactions with mock objects
        verify(filmMapper, times(1)).filmDtoToFilm(any()); // Use any() instead of filmDto
        verify(filmDao, times(1)).save(film);
        verify(filmMapper, times(1)).filmToFilmDto(film);
    }

    @Test
    void testUpdateFilm() throws FilmException{
        // Create a sample FilmDto for update
        FilmDto updatedFilmDto = FilmDto.builder()
                .idFilm(1L)
                .title("Updated Film")
                .author("Updated Author")
                .outDate("2023-11-13")
                .time(120)
                .categories(Arrays.asList(Category.ACTION, Category.DRAMA))
                .build();

        // Configure the behavior of mock objects
        Film existingFilm = new Film(); // Create a Film instance for findById
        when(filmMapper.updateFilmFromDto(updatedFilmDto, existingFilm)).thenReturn(existingFilm);
        when(filmDao.findById(any())).thenReturn(Optional.of(existingFilm));

        // Invoke the method to be tested
        filmService.updateFilm(updatedFilmDto, 1L);

        // Verify the interactions with mock objects
        verify(filmDao, times(1)).findById(1L);
        verify(filmMapper, times(1)).updateFilmFromDto(updatedFilmDto, existingFilm);
        verify(filmDao, times(1)).save(existingFilm);
        // You may need to adjust this depending on your implementation

    }
    @Test
    public void testDeleteFilm() {
        // Exécution de la méthode à tester
        filmService.deleteFilm(1L);

        // Assertions
        // Ajoute des assertions selon la logique de ta méthode
        verify(filmDao, times(1)).deleteById(1L);
    }

    @Test
    public void testSearchFilmsByTitle() {
        // Configuration du comportement simulé du dao
        when(filmDao.searchByTitle(anyString())).thenReturn(Collections.singletonList(new Film()));

        // Exécution de la méthode à tester
        List<Film> films = filmService.searchFilmsByTitle("SearchTerm");

        // Assertions
        assertNotNull(films);
        assertFalse(films.isEmpty());
    }
}
