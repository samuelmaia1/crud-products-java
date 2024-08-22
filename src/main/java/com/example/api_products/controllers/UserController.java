package com.example.api_products.controllers;


import com.example.api_products.domain.user.User;
import com.example.api_products.domain.user.UserRepository;
import com.example.api_products.domain.user.UserRequestPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository repository;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Validated UserRequestPost data){
        try{
            if (!repository.existsByEmail(data.email()) && !repository.existsByLogin(data.login())){
                repository.save(new User(data));
                return ResponseEntity.ok("Usuário criado com sucesso.");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(repository.findAll());
    }
}
