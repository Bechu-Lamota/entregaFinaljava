package com.example.demo.service;

import com.example.demo.exception.ProductoNoEncontrado;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements InterfaceProductService {

    private final ProductRepository repo;

    @Override
    public Product createProduct(Product product) {
        return repo.save(product);
    }

    @Override
    public List<Product> listProducts() {
        return repo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ProductoNoEncontrado("Producto con id " + id + " no encontrado"));
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setCategory(productDetails.getCategory());
        product.setImgUrl(productDetails.getImgUrl());
        product.setStock(productDetails.getStock());
        return repo.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!repo.existsById(id)) {
            throw new ProductoNoEncontrado("Producto con id " + id + " no encontrado");
        }
        repo.deleteById(id);
    }
}
