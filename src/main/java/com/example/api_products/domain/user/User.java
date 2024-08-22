package com.example.api_products.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String login;

    private String email;

    private String password;

    public User(UserRequestPost user){
        this.email = user.email();
        this.login = user.login();
        this.password = user.password();
    }
}
