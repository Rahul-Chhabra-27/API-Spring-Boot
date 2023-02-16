package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserControlller {
    @Autowired
    private UserService userServices;

    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getUsers() {
        return userServices.getUsers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long id) {
        return userServices.getUser(id);
    }
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return userServices.addUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        return userServices.deleteUser(id);
    }
}
