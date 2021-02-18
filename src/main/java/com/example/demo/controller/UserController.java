package com.example.demo.controller;


import com.example.demo.domain.User;
import com.example.demo.dtos.UserDto;
import com.example.demo.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/users")
    public ResponseEntity<UserDto> createOne(@Valid @RequestBody UserDto request, Error error){
        User user = mapper.map(request, User.class);
        return ResponseEntity.ok(request);
    }

    @PostMapping("/users1")
    public ResponseEntity<Object> createOne1(@Valid @RequestBody User request){
        return ResponseEntity.ok(repo.save(request));
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.ok(repo.findAll());
    }


}


