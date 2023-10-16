package com.epf.ratingA.mappers;

import com.epf.ratingA.dto.FilmDto;
import com.epf.ratingA.models.Film;

import java.io.IOException;

public class FilmMapper {
    public static FilmDto fromDto(Film Film)throws IOException {
        return FilmDto.builder()
                .author(Film.getAuthor())
                .time(Film.getTime())
                .title(Film.getTitle())
                .affiche(Film.getAffiche())
                .outDate(Film.getOutDate())
                .categories(Film.getCategory())
                .build();
    }
}
