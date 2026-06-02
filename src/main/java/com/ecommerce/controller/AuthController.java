package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository repo;

    // REGISTER USER
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        repo.save(user);
        return "User registered successfully";
    }

    // LOGIN USER
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User dbUser = repo.findByUsername(user.getUsername()).orElse(null);

        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            return "Login successful";
        }

        return "Invalid username or password";
    }
}