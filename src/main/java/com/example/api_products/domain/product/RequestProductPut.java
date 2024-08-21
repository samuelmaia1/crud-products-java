package com.example.api_products.domain.product;

import org.antlr.v4.runtime.misc.NotNull;

public record RequestProductPut(@NotNull String id,
                                @NotNull String name,
                                @NotNull Integer price_in_cents,
                                @NotNull Integer stock,
                                @NotNull Boolean available
) { }
