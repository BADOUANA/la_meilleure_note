package com.epf.back_end.models;


import com.epf.back_end.enumer.Category;
import jakarta.persistence.*;
import lombok.*;


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
    private String outDate;
    @Column(name = "time")
    private int time;
    @Enumerated(EnumType.STRING)
    @Column(name = "categories")
    private List<Category> categories;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="film")
    private List<Rate> rates = new ArrayList<>();


}
