package com.example.demo.service;

import com.example.demo.model.Order;
import java.util.List;

public interface InterfaceOrderService {
    Order createOrder(Order order);
    List<Order> listOrders();
    Order findOrder(Long id);
}
