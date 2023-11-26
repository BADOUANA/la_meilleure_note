package com.epf.back_end.dto.response;

import com.epf.back_end.enumer.Category;
import com.epf.back_end.models.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class FilmDTO {
    private Long id;
    private String title;
    private String author;
    private LocalDate outDate;
    private int time;
    private List<Category> categories;
    private List<RateDTO> rates;
    private Image image;

}
