package com.example.shopbackend.service.product;

import com.example.shopbackend.exception.DataNotFoundException;
import com.example.shopbackend.exception.InvalidDataException;
import com.example.shopbackend.model.Product;
import com.example.shopbackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        try {
            if (product.getCreatedAt() == null) {
                product.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            }
            return productRepository.save(product);
        } catch (Exception e) {
            throw new InvalidDataException("Invalid product!");
        }
    }

    public void deleteById(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataNotFoundException("Data not found!");
        }
    }

    @Override
    public Product findProductByName(String name) {

        return productRepository.findProductByName(name).orElseThrow(() ->
                new DataNotFoundException("Could not find product with name: %s".formatted(name))
        );
    }

    public void deleteAll() {
        try {
            productRepository.deleteAll();
        } catch (Exception e) {
            throw new DataNotFoundException("Data not found!");
        }
    }
}
