package com.epf.ratingA.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RateDto {
    private Long idRate;
    private float note;
    private String summary;
    private String detailSummary;
}
