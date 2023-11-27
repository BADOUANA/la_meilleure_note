package com.epf.back_end.controllers;

import com.epf.back_end.dto.response.UserDTO;
import com.epf.back_end.enumer.Role;
import com.epf.back_end.exceptions.ResourceNotFoundException;
import com.epf.back_end.interfaces.FilmService;
import com.epf.back_end.interfaces.ImageService;
import com.epf.back_end.interfaces.UserService;
import com.epf.back_end.models.Image;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/images")
@AllArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final UserService userService;
    private final FilmService filmService;


    @PostMapping("/{id}/image")
    public ResponseEntity<Void> addImage(@PathVariable Long id, @RequestParam("image")MultipartFile image) throws IOException{
        try{
            UserDTO userDTO = userService.getUserById(id);
            if (userDTO.getRole() == Role.ADMIN){
                imageService.addImage(image);
                return new ResponseEntity<>( HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable Long id) throws IOException {
        Image image = imageService.getImageById(id);
        return ResponseEntity.ok(image);
    }

    @GetMapping("name/{title}")
    public ResponseEntity<Image> getImageByName(@PathVariable String title) throws IOException {
        Image image = imageService.getImageByName(title);
        return ResponseEntity.ok(image);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Image>> getAllImages(@PathVariable Long id) throws IOException {
        try{
            UserDTO userDTO = userService.getUserById(id);
            if (userDTO.getRole() == Role.ADMIN){
                List<Image> images = imageService.getAllImages();
                return new ResponseEntity<>(images, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



    }

    @PutMapping("user/{userId}/image")
    public ResponseEntity<String> updateUserImage(@PathVariable Long userId, @RequestParam("image") MultipartFile image) {
        try {
            userService.updateUserImage(userId, image);
            return ResponseEntity.ok("Image mise à jour avec succès");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour de l'image.");
        }
    }

    @PutMapping("film/{userId}/{filmId}/image")
    public ResponseEntity<String> updateFilmImage(@PathVariable Long userId, @PathVariable Long filmId, @RequestParam("image") MultipartFile image) {
        try {
            filmService.updateFilmImage(userId, filmId, image);
            return ResponseEntity.ok("Image mise à jour avec succès");
        } catch (IOException | ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour de l'image.");
        }
    }
}
