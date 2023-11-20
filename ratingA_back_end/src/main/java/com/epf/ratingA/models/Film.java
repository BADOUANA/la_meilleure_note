package com.epf.ratingA.models;


import com.epf.ratingA.enumer.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Table(name = "films")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    @ElementCollection(targetClass = Category.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private List<Category> category;

    /*@ManyToMany
    @JoinTable(
            name = "user_film",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "film_idFilm"))
    private List<User> user;*/

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<Rate> rates;

    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    @JsonIgnore
    private Image image;


    public Film (Film film){
        this.setTitle(film.getTitle());
        this.setAuthor(film.getTitle());
        this.setRates(film.getRates());
        this.setOutDate(film.getOutDate());
        this.setCategory(film.getCategory());
        this.setImage(film.getImage());

    }
}
