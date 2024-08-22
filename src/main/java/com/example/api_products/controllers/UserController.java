package com.example.api_products.controllers;


import com.example.api_products.domain.user.User;
import com.example.api_products.domain.user.UserRepository;
import com.example.api_products.domain.user.UserRequestPost;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    UserRepository repository;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Validated UserRequestPost data){
        try{
            User user = new User(data);
            repository.save(user);
            return ResponseEntity.ok("Usuário criado");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
