package com.example.shopbackend.controller;

import com.example.shopbackend.exception.InvalidDataException;
import com.example.shopbackend.model.Product;
import com.example.shopbackend.service.product.ProductService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Slf4j
@Api(value = "Products Controller", tags = "/products")
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    @PreAuthorize("hasAuthority('product:read')")
    public ResponseEntity<List<Product>> getAllProducts() {
        log.info(this.getClass().getName(), " getAllProducts");

        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{productName}")
    public ResponseEntity<Product> getProductByName(
            @PathVariable @Valid @NotNull @Size(min = 3) String productName, BindingResult bindingResult) {
        log.info(this.getClass().getName(), " getProductByName");

        if (bindingResult.hasErrors()) {
            throw new InvalidDataException("Invalid product name");
        }

        return ResponseEntity.ok(productService.findProductByName(productName));
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('product:write')")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        log.info(this.getClass().getName(), " createProduct");

        log.info(product.toString());

        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping()
    @PreAuthorize("hasAuthority('product:delete')")
    public ResponseEntity deleteAll() {
        log.info(this.getClass().getName(), " deleteAll");

        productService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('product:delete')")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        log.info(this.getClass().getName(), " deleteById");

        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
