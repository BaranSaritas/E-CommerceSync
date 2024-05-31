package com.E_CommerceSync.E_CommerceSync.service.impl;

import com.E_CommerceSync.E_CommerceSync.model.Order;
import com.E_CommerceSync.E_CommerceSync.repository.OrderRepository;
import com.E_CommerceSync.E_CommerceSync.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {

        return repository.findById(orderId).orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        return repository.save(order);
    }
}
