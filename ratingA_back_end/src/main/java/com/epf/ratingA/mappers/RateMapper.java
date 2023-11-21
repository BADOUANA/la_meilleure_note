package com.epf.ratingA.mappers;

import com.epf.ratingA.dto.RateDto;
import com.epf.ratingA.models.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.io.IOException;

@Mapper
public interface RateMapper {
    @Mapping(target = "rate.id", source = "user.id")
    @Mapping(target = "rate.id", source = "film.id")
     static RateDto fromDto(Rate rate)throws IOException {
        return RateDto.builder()
                .note(rate.getNote())
                .summary(rate.getSummary())
                .detailSummary(rate.getDetailSummary())
                .build();
    }

    RateMapper INSTANCE = Mappers.getMapper(RateMapper.class);
    Rate fromRateDto(RateDto rateDto, Long id)throws IOException;
}
