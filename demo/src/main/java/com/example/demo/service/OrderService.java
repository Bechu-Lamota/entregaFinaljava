package com.example.demo.service;

import com.example.demo.exception.StockInsuficiente;
import com.example.demo.model.Order;
import com.example.demo.model.OrderList;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements InterfaceOrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        for (OrderList ol : order.getList()) {
            Product product = ol.getProduct();
            if (ol.getQuantity() > product.getStock()) {
                throw new StockInsuficiente("Stock insuficiente para " + product.getName());
            }
        }

        // Descontamos stock
        for (OrderList ol : order.getList()) {
            Product product = ol.getProduct();
            product.setStock(product.getStock() - ol.getQuantity());
        }

        order.setStatus("EN_PROCESO");
        return orderRepository.save(order);
    }

    @Override
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
    }
}
