package com.example.eksamen2024cpflbe.controllers;

import com.example.eksamen2024cpflbe.models.User;
import com.example.eksamen2024cpflbe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin
@RestController
public class UserRestController
{
    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        Optional<User> existingUsername = userRepository.findByUsername(user.getUsername());

        if ( existingUsername.isPresent() )
        {
            System.out.println("Username or already exists in the database");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else
        {
            user.setCreated(java.time.LocalDateTime.now());
            user.setUpdated(java.time.LocalDateTime.now());
            User savedUser = userRepository.save(user);
            System.out.println("User with name: " + savedUser.getName() + " and username: " + savedUser.getUsername() + ", has been saved to the database");
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
    }

}
