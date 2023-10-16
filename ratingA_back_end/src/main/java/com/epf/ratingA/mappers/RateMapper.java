package com.epf.ratingA.mappers;

import com.epf.ratingA.dto.RateDto;
import com.epf.ratingA.models.Rate;

import java.io.IOException;

public class RateMapper {
    public static RateDto fromDto(Rate rate)throws IOException {
        return RateDto.builder()
                .note(rate.getNote())
                .summary(rate.getSummary())
                .detailSummary(rate.getDetailSummary())
                .build();
    }
}
