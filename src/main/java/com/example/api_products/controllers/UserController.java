package com.example.api_products.controllers;

import com.example.api_products.domain.user.User;
import com.example.api_products.domain.user.UserRepository;
import com.example.api_products.domain.user.UserRequestPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Validated UserRequestPost data){
        System.out.print(data);
        try{
            if (!repository.existsByEmail(data.email()) && !repository.existsByLogin(data.login())){
                User user = new User(data);
                user.setPassword(encoder.encode(user.getPassword()));
                return ResponseEntity.ok("Usuário criado com sucesso.");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validatePassword(@RequestParam String login,
                                                    @RequestParam String password){

        Optional<User> optionalUser = repository.findByLogin(login);
        if (optionalUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        boolean valid = encoder.matches(password, optionalUser.get().getPassword());

        HttpStatus status = valid? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(valid);
    }

    @GetMapping
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(repository.findAll());
    }
}
