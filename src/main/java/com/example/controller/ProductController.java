package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.model.Product;
import com.example.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // GET: View all products
    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    // GET: Find product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return service.getProductById(id);
    }

    // POST: Add a new product
    @PostMapping
    public Product addProduct(@Valid @RequestBody Product product) {
        return service.addProduct(product);
    }

    // PUT: Update product details
    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody Product product) {
        return service.updateProduct(id, product);
    }

    // DELETE: Delete a product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return service.deleteProduct(id);
    }
}
