package com.epf.ratingA.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "film")
@NoArgsConstructor
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
    private Integer time;
    @Column(name = "category")
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Film film;

    @OneToMany(mappedBy = "rate", cascade = CascadeType.ALL)
    private List<Rate> rate;



}
