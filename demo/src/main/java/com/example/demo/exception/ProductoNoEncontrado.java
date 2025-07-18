package com.example.demo.exception;

public class ProductoNoEncontrado extends RuntimeException {
    public ProductoNoEncontrado() {
        super("Producto no encontrado.");
    }

    public ProductoNoEncontrado(String message) {
        super(message);
    }
}
