package com.example.userregistrationjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userregistrationjwt.entity.User;
import com.example.userregistrationjwt.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/me")
    public User getMyInfo(Authentication authentication) {
        return userRepo.findByEmail(authentication.getName());
    }
}