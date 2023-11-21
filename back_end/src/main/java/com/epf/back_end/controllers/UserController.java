package com.epf.back_end.controllers;

import com.epf.back_end.dao.UserDao;
import com.epf.back_end.exceptions.ResourceNotFoundException;
import com.epf.back_end.models.Rate;
import com.epf.back_end.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserDao userDao;

    @GetMapping("/list")
    public List<User> getUsers(){
        return (List<User>) userDao.findAll();
    }

    @PostMapping
    void addUser(@RequestBody User user){
        userDao.save(user);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        User user = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        return ResponseEntity.ok().body(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id,
                                                   @RequestBody User user) throws ResourceNotFoundException {
        User existUser = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        existUser.setEmail(user.getEmail());
        existUser.setLastName(user.getLastName());
        existUser.setFirstName(user.getFirstName());
        final User updatedUser = userDao.save(existUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        User user = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        userDao.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/{id}/myRates")
    public ResponseEntity<List<Rate>> getRatesByUserId(@PathVariable Long id){
        List<Rate> rates = userDao.getAllRatesFromUser(id);
        return ResponseEntity.ok().body(rates);
    }
}
