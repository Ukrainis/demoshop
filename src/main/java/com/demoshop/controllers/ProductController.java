package com.demoshop.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoshop.entities.Product;
import com.demoshop.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/addProduct")
    public ResponseEntity<Product> addProductEndpoint(@RequestBody @Valid Product product) {
        Product savedProduct = productService.createProduct(product);
        URI newUri = URI.create(String.format("/products/%d", savedProduct.getId()));
        return ResponseEntity.created(newUri).body(savedProduct);
    }

    @GetMapping(path = "/getAllProducts")
    public ResponseEntity<List<Product>> returnAllProductsEndpoint() {
        List<Product> listOfProducts = productService.getAllProducts();
        return ResponseEntity.ok(listOfProducts);
    }
}
