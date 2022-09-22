package com.demoshop.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoshop.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
