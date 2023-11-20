package com.epf.ratingA.dto;

import com.epf.ratingA.enumer.Category;
import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.Image;
import com.epf.ratingA.models.Rate;
import com.epf.ratingA.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmDto {
    private Long idFilm;
    private String title;
    private String author;
    private String outDate;
    private int time;
    private Long imageId;
    private List<Rate> rates;
    private List<Category> categories;

    // You can include other fields as needed
}
