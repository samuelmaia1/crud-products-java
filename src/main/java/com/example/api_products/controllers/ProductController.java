package com.example.api_products.controllers;

import com.example.api_products.domain.product.Product;
import com.example.api_products.domain.product.ProductRepository;
import com.example.api_products.domain.product.RequestProduct;
import com.example.api_products.domain.product.RequestProductPut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } catch (Exception e){
            System.out.println("Erro ao adicionar novo produto");
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir registro no banco de dados");
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateProduct(@RequestBody @Validated RequestProductPut data){
        try{
            repository.deleteById(data.id());
            Product updatedProduct = new Product(data);
            repository.save(updatedProduct);

            return ResponseEntity.status(HttpStatus.CREATED).body("Produto atualizado.");
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar produto.");
        }
    }

}
