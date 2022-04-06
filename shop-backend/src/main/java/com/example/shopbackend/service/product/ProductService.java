package com.example.shopbackend.service.product;

import com.example.shopbackend.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product save(Product product);

    void deleteById(Long id);

    Product findProductByName(String name);

    void deleteAll();
}
