package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private final UserRepo userRepo;


    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> findByUsername(String s) {
        return this.userRepo.findByUsername(s);
    }
}
