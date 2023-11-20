package com.epf.ratingA.mappers;

import com.epf.ratingA.dto.FilmDto;
import com.epf.ratingA.models.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface FilmMapper {
    @Mapping(source = "categories", target = "category")
    FilmDto filmToFilmDto(Film film);

    @Mapping(source = "category", target = "categories")
    Film filmDtoToFilm(FilmDto filmDto);

    @Mapping(target = "idFilm", ignore = true)
    @Mapping(target = "rates", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(source = "categories ", target = "category")

    Film updateFilmFromDto(FilmDto filmDto, @MappingTarget Film film);
}
