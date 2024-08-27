package com.example.api_products.domain.product;

public record RequestProductPut(
        String id,
        String name,
        Integer price_in_cents,
        Integer stock,
        Boolean available
) { }
