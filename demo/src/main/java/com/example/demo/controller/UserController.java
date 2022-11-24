package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = new ArrayList<User>();

            userRepository.findAll().forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User _user = userRepository
                    .save(new User(user.getUsername(), user.getFirst_name(), user.getLast_name()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setFirst_name(user.getFirst_name());
            _user.setLast_name(user.getLast_name());
            _user.setUsername(user.getUsername());
           // _user.setUsername(createUsername(user));
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


/*    private String createUsername(User user){
        String username = user.getFirst_name().charAt(0) + user.getLast_name();
       return username.toLowerCase();
    }*/
}
