package com.epf.ratingA.models;


import com.epf.ratingA.enumer.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Entity
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "role")
    private Role role;
    @Column(name = "email")
    private String email;
    @Column(name = "birthdate")
    private Instant birthdate;
    @Column(name = "sex")
    private String sexe;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "film")
    @JsonIgnore
    private List<Film> films;

    @OneToMany(mappedBy = "Rate",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Rate> rates;

    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    @JsonIgnore
    private Image image;



}
