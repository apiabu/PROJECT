package com.example.lendingsystem.service;

import com.example.lendingsystem.model.User;
import com.example.lendingsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repo;

    public AuthService(UserRepository repo) {
        this.repo = repo;
    }

    public User login(String username, String password) {
        User user = repo.findByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // invalid
    }

    public User register(User user) {
        return repo.save(user);
    }
}
