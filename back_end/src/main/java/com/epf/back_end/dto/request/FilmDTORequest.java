package com.epf.back_end.dto.request;

import com.epf.back_end.dto.response.RateDTO;
import com.epf.back_end.enumer.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class FilmDTORequest {
    private String title;
    private String author;
    private LocalDate outDate;
    private int time;
    private List<Category> categories;
    private Long imageId;
}
