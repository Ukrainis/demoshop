package com.demoshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoshop.entities.Product;
import com.demoshop.interfaces.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
