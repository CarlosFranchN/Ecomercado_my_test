package com.example.gateway_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import  org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gateway_service.model.Product;
import com.example.gateway_service.service.ProductService;



@RestController
@RequestMapping("/produtos")
public class ProductController {
    private final ProductService productService;
    
    public ProductController (ProductService productService){
        this.productService = productService;
    }


    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id){
        return productService.getProductById(id);
    }


    @GetMapping()
    public List<Product> getAllProductsList(){
        return  productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product newProduct){
        Product update = productService.updateProduct(id, newProduct);
        if (update != null){
            return ResponseEntity.ok(update);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}

