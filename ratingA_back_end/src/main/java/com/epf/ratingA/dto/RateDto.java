package com.epf.ratingA.dto;

import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateDto {
    private Long idRate;
    private float note;
    private String summary;
    private String detailSummary;
    private Film film;
    private User user;
}
