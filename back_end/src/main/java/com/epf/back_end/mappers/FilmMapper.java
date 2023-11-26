package com.epf.back_end.mappers;

import com.epf.back_end.dto.request.FilmDTORequest;
import com.epf.back_end.dto.response.FilmDTO;
import com.epf.back_end.models.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", implementationName = "FilmMapperImpl")
public interface FilmMapper {
    FilmDTO filmToFilmDTO(Film film);

    Film filmDTOToFilm(FilmDTO filmDTO);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rates", ignore = true)
    Film filmDTOToFilm(FilmDTORequest filmDTORequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rates", ignore = true)
    void updateFilmFromDTO(FilmDTORequest filmDTORequest, @MappingTarget Film film);

}
