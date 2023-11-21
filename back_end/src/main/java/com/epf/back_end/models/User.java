package com.epf.back_end.models;

import com.epf.back_end.enumer.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "role")
    private Role role;
    @Column(name = "birthdate")
    private Instant birthdate;
    @Column(name = "sex")
    private String sex;
    @Column(name = "password")
    private String password;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="user")
    private List<Rate> rates = new ArrayList<>();

    public User() {

    }


}
