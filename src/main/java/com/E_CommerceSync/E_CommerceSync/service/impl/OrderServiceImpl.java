package com.E_CommerceSync.E_CommerceSync.service.impl;

import com.E_CommerceSync.E_CommerceSync.config.kafka.KafkaProducerConfig;
import com.E_CommerceSync.E_CommerceSync.dto.request.OrderRequest;
import com.E_CommerceSync.E_CommerceSync.mapper.OrderMapper;
import com.E_CommerceSync.E_CommerceSync.model.Order;
import com.E_CommerceSync.E_CommerceSync.repository.OrderRepository;
import com.E_CommerceSync.E_CommerceSync.service.OrderService;
import com.E_CommerceSync.E_CommerceSync.utils.aop.log.LoggingAspect;
import com.E_CommerceSync.E_CommerceSync.utils.helper.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final KafkaProducerConfig kafkaProducerConfig;

    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {

        return repository.findById(orderId).orElse(null);
    }
    @LoggingAspect
    @Override
    @KafkaListener(topics = "pending-orders",containerFactory ="createListenerContainerFactoryModel")
    public void getOrdersByKafka(OrderRequest order){
        Order saveOrder  = mapper.orderRequestToOrder(order);
        repository.save(saveOrder);
    }


    @Override
    @LoggingAspect
    public void createOrder(OrderRequest order) {
        kafkaProducerConfig.produceOrder(order);
    }
}
