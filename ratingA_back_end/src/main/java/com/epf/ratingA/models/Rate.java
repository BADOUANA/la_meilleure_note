package com.epf.ratingA.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "rates")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRate;
    @Column(name = "name")
    private String name;
    @Column(name = "note")
    private float note;
    @Column(name = "summary")
    private String summary;
    @Column(name = "detail_summary")
    private String detailSummary;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
