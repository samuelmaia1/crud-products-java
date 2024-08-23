package com.example.api_products.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    Optional<User> findByLogin(String login);
    Boolean existsByEmail(String email);
    Boolean existsByLogin(String login);
}
