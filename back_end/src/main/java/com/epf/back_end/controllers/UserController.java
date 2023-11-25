package com.epf.back_end.controllers;

import com.epf.back_end.dao.UserDao;
import com.epf.back_end.dto.request.UserDTORequest;
import com.epf.back_end.dto.response.UserDTO;
import com.epf.back_end.exceptions.ResourceNotFoundException;
import com.epf.back_end.interfaces.impl.UserServiceImpl;
import com.epf.back_end.models.Rate;
import com.epf.back_end.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserDao userDao;
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        try {
            UserDTO userDTO = userServiceImpl.getUserById(id);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            List<UserDTO> userDTOs = userServiceImpl.getAllUsers();
            return new ResponseEntity<>(userDTOs, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTORequest userDTORequest) {
        try {
            UserDTO createdUser = userServiceImpl.createUser(userDTORequest);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTORequest userDTORequest) {
        try {
            UserDTO updatedUser = userServiceImpl.updateUser(id, userDTORequest);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/myRates")
    public ResponseEntity<List<Rate>> getRatesByUserId(@PathVariable Long id){
        List<Rate> rates = userDao.getAllRatesFromUser(id);
        return ResponseEntity.ok().body(rates);
    }
}
