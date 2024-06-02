package com.E_CommerceSync.E_CommerceSync.service;

import com.E_CommerceSync.E_CommerceSync.dto.request.OrderRequest;
import com.E_CommerceSync.E_CommerceSync.model.Order;
import com.E_CommerceSync.E_CommerceSync.utils.aop.log.LoggingAspect;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    Order getOrderById(Long orderId);

    @LoggingAspect
    @KafkaListener(topics = "model-topic",containerFactory ="createListenerContainerFactoryModel")
    void getOrdersByKafka(OrderRequest order);

    void createOrder(OrderRequest order);
}
