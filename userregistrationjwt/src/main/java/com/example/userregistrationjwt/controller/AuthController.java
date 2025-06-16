package com.example.userregistrationjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.userregistrationjwt.config.JwtUtil;
import com.example.userregistrationjwt.dto.AuthResponse;
import com.example.userregistrationjwt.dto.RegisterRequest;
import com.example.userregistrationjwt.entity.User;
import com.example.userregistrationjwt.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userRepo.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        // Fetch user by email
        User user = userRepo.findByEmail(loginRequest.getEmail());
        System.out.println(user);
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getEmail());

        // Return token in response body
        return ResponseEntity.ok().body(new AuthResponse(token));
    }
}