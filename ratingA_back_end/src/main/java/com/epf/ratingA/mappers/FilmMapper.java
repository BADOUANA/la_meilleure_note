package com.epf.ratingA.mappers;

import com.epf.ratingA.dto.FilmDto;
import com.epf.ratingA.models.Film;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.io.IOException;

@Mapper
public interface FilmMapper {
    public static FilmDto fromDto(Film Film)throws IOException {
        return FilmDto.builder()
                .author(Film.getAuthor())
                .time(Film.getTime())
                .title(Film.getTitle())
                //.affiche(Film.getAffiche())
                .outDate(Film.getOutDate())
                .categories(Film.getCategory())
                .build();
    }
    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);
    Film fromFilmDto(FilmDto filmDto, Long id) throws IOException;
}
