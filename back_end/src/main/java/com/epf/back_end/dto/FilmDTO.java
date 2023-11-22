package com.epf.back_end.dto;

import com.epf.back_end.enumer.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class FilmDTO {
    private Long id;
    private String title;
    private String author;
    private String outDate;
    private int time;
    private List<Category> categories;
    private List<RateDTO> rates;

}
