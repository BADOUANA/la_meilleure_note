package com.epf.ratingA.mappers;

import com.epf.ratingA.dto.FilmDto;
import com.epf.ratingA.models.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component("FilmMapper")
public interface FilmMapper {
    @Mapping(source = "categories", target = "category")
    @Mapping(source = "imageId", target = "image.id")
    FilmDto filmToFilmDto(Film film);

    @Mapping(source = "category", target = "categories")
    @Mapping(source = "image.id", target = "imageId")

    Film filmDtoToFilm(FilmDto filmDto);
}
