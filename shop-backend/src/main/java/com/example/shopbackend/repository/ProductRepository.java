package com.example.shopbackend.repository;

import com.example.shopbackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin("*")
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByName(String name);
}