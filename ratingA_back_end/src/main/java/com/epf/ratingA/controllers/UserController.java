package com.epf.ratingA.controllers;

import com.epf.ratingA.models.Film;
import com.epf.ratingA.models.User;
import com.epf.ratingA.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/listUsers")
    public List<User> getAllUsers(){return userService.findAll();}
    @GetMapping("/{id}/userfilms")
    public List<Film> getFilmsByUserId(@RequestParam Long userId){
        return (userId != null)? userService.findAllFilmsByUserId(userId): null;
    }
}
