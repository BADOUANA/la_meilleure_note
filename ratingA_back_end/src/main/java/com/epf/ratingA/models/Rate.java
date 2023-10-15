package com.epf.ratingA.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "rate")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRate;
    @Column(name = "note")
    private float note;
    @Column(name = "summary")
    private String summary;
    @Column(name = "detail_summary")
    private String detailSummary;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
}
