package com.E_CommerceSync.E_CommerceSync.controller;

import com.E_CommerceSync.E_CommerceSync.dto.request.OrderRequest;
import com.E_CommerceSync.E_CommerceSync.model.Order;
import com.E_CommerceSync.E_CommerceSync.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {

            return ResponseEntity.ok(  orderService.getOrderById(orderId));

    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest order) {
         orderService.createOrder(order);
        return ResponseEntity.ok().build();
    }
}
