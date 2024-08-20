package com.example.api_products.domain.product;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "products")
@Entity(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Product {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private Integer price_in_cents;

    private Integer stock;

    private Boolean available;

    public Product(RequestProduct requestProduct){
        this.name = requestProduct.name();
        this.stock = requestProduct.stock();
        this.price_in_cents = requestProduct.price_in_cents();
        this.available = requestProduct.available();
    }
}
