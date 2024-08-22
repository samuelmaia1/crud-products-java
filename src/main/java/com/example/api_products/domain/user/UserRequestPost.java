package com.example.api_products.domain.user;

import org.antlr.v4.runtime.misc.NotNull;

public record UserRequestPost(@NotNull String login,
                              @NotNull String email,
                              @NotNull String password
) { }
