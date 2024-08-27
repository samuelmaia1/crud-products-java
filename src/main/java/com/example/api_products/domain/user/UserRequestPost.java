package com.example.api_products.domain.user;

public record UserRequestPost(
        String login,
        String email,
        String password
) { }
