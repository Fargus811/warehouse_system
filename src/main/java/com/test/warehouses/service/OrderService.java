package com.test.warehouses.service;

import com.test.warehouses.model.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);

    Order findOrderById(Long orderId);

    List<Order> findAll();

    void deleteOrderById(Long orderId);
}

