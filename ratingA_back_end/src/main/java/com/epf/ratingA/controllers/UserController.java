package com.epf.ratingA.controllers;

import com.epf.ratingA.dto.UserDto;
import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.User;
import com.epf.ratingA.services.ServiceUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final ServiceUser serviceUser;

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = serviceUser.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}/films")
    public ResponseEntity<List<Film>> getFilmsByUserId(@PathVariable Long id) {
        List<Film> films = (id != null) ? serviceUser.findAllFilmsByUserId(id) : null;
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        serviceUser.createUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        serviceUser.updateUser(userDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        serviceUser.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}