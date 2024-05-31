package com.E_CommerceSync.E_CommerceSync.service;

import com.E_CommerceSync.E_CommerceSync.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    Order getOrderById(Long orderId);

    Order createOrder(Order order);
}
