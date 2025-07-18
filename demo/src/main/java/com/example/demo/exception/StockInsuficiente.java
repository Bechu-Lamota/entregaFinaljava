// exceptions/StockInsuficienteException.java
package com.example.demo.exception;

public class StockInsuficiente extends RuntimeException {
    public StockInsuficiente() {
        super("Stock insuficiente para completar la operación.");
    }

    public StockInsuficiente(String message) {
        super(message);
    }
}
