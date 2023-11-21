package com.epf.ratingA.controllers;

import com.epf.ratingA.dto.UserDto;
import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.User;
import com.epf.ratingA.services.UserService;
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
    private final UserService userService;


    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @CrossOrigin("*")

    @GetMapping("/{id}")
    public ResponseEntity<List<Film>> getUserById(@PathVariable Long id) {
        List<Film> films = (id != null) ? userService.findByUserId(id) : null;
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        userService.updateUser(userDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}