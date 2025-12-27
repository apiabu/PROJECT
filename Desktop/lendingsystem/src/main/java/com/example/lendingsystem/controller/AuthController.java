package com.example.lendingsystem.controller;

import com.example.lendingsystem.model.User;
import com.example.lendingsystem.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public User login(@RequestBody User user){
        User logged = service.login(user.getUsername(), user.getPassword());
        if(logged == null){
            throw new RuntimeException("Invalid username or password");
        }
        return logged;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return service.register(user);
    }
}
