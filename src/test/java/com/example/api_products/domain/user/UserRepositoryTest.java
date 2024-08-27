package com.example.api_products.domain.user;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("")
    void findByEmailSuccess() {
        String email = "teste@gmail.com";
        UserRequestPost userRequestPost = new UserRequestPost("loginteste", email, "senha");
        this.createUser(userRequestPost);
        User foundedUser = this.userRepository.findByEmail(email);

        assertThat(foundedUser != null);
    }

    @Test
    @DisplayName("")
    void findByEmailFail() {
        String email = "teste@gmail.com";

        User foundedUser = this.userRepository.findByEmail(email);

        assertThat(foundedUser == null);
    }

    @Test
    @DisplayName("")
    void findByLoginSuccess() {
        String login = "loginteste";
        UserRequestPost userRequestPost = new UserRequestPost(login, "email@teste", "senha");
        this.createUser(userRequestPost);
        Optional<User> foudedUser = this.userRepository.findByLogin(login);

        assertThat(foudedUser.isPresent());
    }

    @Test
    void existsByEmail() {
    }

    @Test
    void existsByLogin() {
    }

    private User createUser(UserRequestPost data){
        User user = new User(data);
        this.entityManager.persist(user);
        return user;
    }
}