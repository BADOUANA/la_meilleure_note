package com.epf.ratingA.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bytea")
    @Lob
    private byte[] bytes;
    @Column(name = "title")
    private String title;

    @OneToOne(mappedBy = "image")
    @JsonIgnore
    private User user;

    @OneToOne(mappedBy = "image")
    @JsonIgnore
    private Film film;
}
