package com.epf.back_end.mappers;

import com.epf.back_end.dto.RateDTO;
import com.epf.back_end.models.Rate;

import java.util.List;
import java.util.stream.Collectors;

public class RateMapper {
    public static RateDTO toDTO(Rate rate) {
        return RateDTO.builder()
                .idRate(rate.getId())
                .name(rate.getName())
                .note(rate.getNote())
                .summary(rate.getSummary())
                .detailSummary(rate.getDetailSummary())
                // Assume these fields are mapped using FilmMapper and UserMapper
                // .film(FilmMapper.toDTO(rate.getFilm()))
                // .user(UserMapper.toDTO(rate.getUser()))
                .build();
    }

    public static List<RateDTO> toDTOList(List<Rate> rates) {
        return rates.stream().map(RateMapper::toDTO).collect(Collectors.toList());
    }

    public static Rate toEntity(RateDTO rateDTO) {
        return Rate.builder()
                .id(rateDTO.getIdRate())
                .name(rateDTO.getName())
                .note(rateDTO.getNote())
                .summary(rateDTO.getSummary())
                .detailSummary(rateDTO.getDetailSummary())
                // Exclude film and user, as they are likely not set directly from the DTO
                .build();
    }

    public static List<Rate> toEntityList(List<RateDTO> rateDTOS) {
        return rateDTOS.stream().map(RateMapper::toEntity).collect(Collectors.toList());
    }
}
