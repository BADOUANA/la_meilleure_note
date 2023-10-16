package com.epf.ratingA.models;


import com.epf.ratingA.enumer.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Table(name = "film")
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
    private Integer time;
    @Column(name = "category")
    private List<Category> category;
    @Column(name = "image")
    @Lob
    private byte[] affiche; // Pour stocker l'image en tant que BLOB (Binary Large Object)


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Film film;

    @OneToMany(mappedBy = "rate", cascade = CascadeType.ALL)
    private List<Rate> rate;



}
