package com.epf.ratingA.dto;

import com.epf.ratingA.enumer.Category;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class FilmDto {
    private Long idFilm;
    private String title;
    private String author;
    private String outDate;
    private Integer time;
    private byte[] affiche;
    private List<Category> categories;
}
