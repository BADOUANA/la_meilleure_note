package com.epf.ratingA.models;


import com.epf.ratingA.enumer.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Table(name = "films")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFilm;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "out_date")
    private String outDate;
    @Column(name = "time")
    private int time;
    @Column(name = "category")
    private List<Category> category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "rate", cascade = CascadeType.ALL)
    private List<Rate> rates;

    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    @JsonIgnore
    private Image image;


}
