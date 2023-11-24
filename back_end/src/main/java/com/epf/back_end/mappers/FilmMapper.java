package com.epf.back_end.mappers;

import com.epf.back_end.dto.FilmDTO;
import com.epf.back_end.models.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", implementationName = "FilmMapperImpl")
public interface FilmMapper {
    /*public static FilmDTO toDTO(Film film) {
        return FilmDTO.builder()
                .id(film.getId())
                .title(film.getTitle())
                .author(film.getAuthor())
                .outDate(film.getOutDate())
                .time(film.getTime())
                .categories(film.getCategories())
                .rates(RateMapper.toDTOList(film.getRates()))
                .build();
    }

    public static Film toEntity(FilmDTO filmDTO) {
        return Film.builder()
                .id(filmDTO.getId())
                .title(filmDTO.getTitle())
                .author(filmDTO.getAuthor())
                .outDate(filmDTO.getOutDate())
                .time(filmDTO.getTime())
                .categories(filmDTO.getCategories())
                // Exclude rates, as they are likely not set directly from the DTO
                .build();
    }

    public static List<Film> toEntityList(List<FilmDTO> filmDTOS) {
        return filmDTOS.stream().map(FilmMapper::toEntity).collect(Collectors.toList());
    }*/
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rates", ignore = true)
    FilmDTO filmToFilmDTO(Film film);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rates", ignore = true)
    Film filmDTOToFilm(FilmDTO filmDTO);

    List<FilmDTO> filmsToFilmDTOs(List<Film> films);
    List<Film> filmDTOsToFilms(List<FilmDTO> filmDTOs);
}
