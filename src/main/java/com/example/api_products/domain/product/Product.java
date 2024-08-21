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

    public Product(RequestProductPut requestProductPut){
        this.id = requestProductPut.id();
        this.name = requestProductPut.name();
        this.stock = requestProductPut.stock();
        this.price_in_cents = requestProductPut.price_in_cents();
        this.available = requestProductPut.available();
    }

    @Override
    public String toString(){
        return this.name + " R$" + this.price_in_cents/100 + " Estoque: " + this.stock;
    }
}
