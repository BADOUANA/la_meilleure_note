package com.epf.ratingA.mappers;

import com.epf.ratingA.dto.FilmDto;
import com.epf.ratingA.models.Film;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.io.IOException;

@Mapper
public interface FilmMapper {

    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);
    Film fromFilmDto(FilmDto filmDto, Long id) throws IOException;
}
