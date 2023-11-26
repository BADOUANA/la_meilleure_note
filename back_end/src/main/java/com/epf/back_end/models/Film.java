package com.epf.back_end.models;


import com.epf.back_end.enumer.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "out_date")
    private LocalDate outDate;
    @Column(name = "time")
    private int time;
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private List<Category> categories;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="film")
    @JsonIgnore
    private List<Rate> rates = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    @JsonIgnore
    private Image image;


}
