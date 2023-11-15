package com.epf.ratingA.mappers;

import com.epf.ratingA.dto.FilmDto;
import com.epf.ratingA.models.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component("filmMapper")
public interface FilmMapper {
    @Mapping(source = "category", target = "categories")
    @Mapping(source = "image.id", target = "imageId")
    FilmDto filmToFilmDto(Film film);

    @Mapping(source = "categories", target = "category")
    @Mapping(source = "imageId", target = "image.id")

    Film filmDtoToFilm(FilmDto filmDto);
}
