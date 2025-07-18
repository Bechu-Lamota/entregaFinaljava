package com.example.demo.service;

import com.example.demo.model.Product;
import java.util.List;

public interface InterfaceProductService {
    Product createProduct(Product product);
    List<Product> listProducts();
    Product getProductById(Long id);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}