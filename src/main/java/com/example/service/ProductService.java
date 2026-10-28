package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Exception.ProductNotFoundException;
import com.example.model.Product;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private long nextId = 1;

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getProductId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                    new ProductNotFoundException(
                        "Product not found with ID: " + id));
    }

    public Product addProduct(Product product) {
        product.setProductId(nextId++);
        products.add(product);
        return product;
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);

        existingProduct.setProductName(
                updatedProduct.getProductName());
        existingProduct.setCategory(
                updatedProduct.getCategory());
        existingProduct.setStock(
                updatedProduct.getStock());
        existingProduct.setPrice(
                updatedProduct.getPrice());

        return existingProduct;
    }

    public String deleteProduct(Long id) {
        Product product = getProductById(id);
        products.remove(product);

        return "Product deleted successfully with ID: " + id;
    }
}
