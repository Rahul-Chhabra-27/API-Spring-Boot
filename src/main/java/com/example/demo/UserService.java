package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private  UserRepository userORM;
    public  ResponseEntity<List<User>> getUsers() {
        try{
            List<User> users = new ArrayList<>();
            userORM.findAll().forEach(users::add);
            if(users.isEmpty()) {
                return new ResponseEntity<List<User>>(users, HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(users,HttpStatus.ACCEPTED);
            }
        }
        catch(Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Optional<User>> getUser(Long id) {
        Optional<User> user = userORM.findById(id);
        if(user.isPresent()) {
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> addUser(User user) {
        User newUser = userORM.save(user);
        return new ResponseEntity<>(newUser,HttpStatus.OK);
    }

    public ResponseEntity<User> deleteUser(Long id) {
        try {
            userORM.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception exception) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
