package com.example.demo.controller;


import com.example.demo.domain.User;
import com.example.demo.dtos.UserDto;
import com.example.demo.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController

public class UserController {

    private final UserRepo repo;
    private final ModelMapper mapper = new ModelMapper();

    public UserController(UserRepo repo) {
        this.repo = repo;
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<User> createOne(@Valid @RequestBody UserDto request, Error error) {
        User user = mapper.map(request, User.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return ResponseEntity.ok(repo.save(user));
    }

   /* @PostMapping("/login")
    public ResponseEntity<UserDto> login(@Valid @RequestBody UserDto request, Error error){
        //User user = mapper.map(request, User.class);
        return ResponseEntity.ok(request);
    }*/

    /*@PostMapping("/users1")
    public ResponseEntity<Object> createOne1(@Valid @RequestBody User request) {
        return ResponseEntity.ok(repo.save(request));
    }*/

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getAll(Authentication auth) {

        System.out.println("test");
        return ResponseEntity.ok(auth.getPrincipal());
    }

    @GetMapping("/users/one")
    //@PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Object> getOne(Authentication auth) {

        System.out.println("test");
        return ResponseEntity.ok(repo.findById(34L));
    }


}


