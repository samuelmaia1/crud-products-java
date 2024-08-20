package com.example.api_products.controllers;

import com.example.api_products.domain.product.Product;
import com.example.api_products.domain.product.ProductRepository;
import com.example.api_products.domain.product.RequestProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts(){

        var allProducts = repository.findAll();
        return ResponseEntity.ok(allProducts);

    }

    @PostMapping("/add")
    public ResponseEntity registerProduct(@RequestBody @Validated RequestProduct data){

        Product product = new Product(data);
        try{
            repository.save(product);
        } catch (Exception e){
            System.out.println("Erro ao adicionar novo produto");
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(product);
    }
}
