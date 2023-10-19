package com.epf.ratingA.dto;

import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.User;
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
    private Film film;
    private User user;
}
