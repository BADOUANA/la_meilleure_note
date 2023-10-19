package com.epf.ratingA.dto;

import com.epf.ratingA.enumer.Category;
import com.epf.ratingA.models.User;
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
    //private byte[] affiche;
    private List<User> users;
    private List<Category> categories;
}
