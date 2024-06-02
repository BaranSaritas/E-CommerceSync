package com.E_CommerceSync.E_CommerceSync.config.kafka;


import com.E_CommerceSync.E_CommerceSync.dto.request.OrderRequest;
import com.E_CommerceSync.E_CommerceSync.model.Order;
import com.E_CommerceSync.E_CommerceSync.utils.helper.OrderStatus;
import io.confluent.kafka.serializers.KafkaJsonSerializer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Properties;

@Component
@RequiredArgsConstructor
public class KafkaProducerConfig {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerConfig.class);
    @Value("${spring.kafka.producer.topic-name}")
    private String topic;
    private KafkaProducer<String, OrderRequest> producer;


    @PostConstruct
    private void init() {
        Properties config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://localhost:29092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSerializer.class.getName());
        producer = new KafkaProducer<>(config);
    }


    public void produceOrder(OrderRequest orderRequest) {
        try {
            ProducerRecord<String, OrderRequest> record = new ProducerRecord<String, OrderRequest>(topic, orderRequest);
            producer.send(record);

            logger.info("Sent message: {}", record.toString());
        } catch (Exception e) {
            logger.error("Error occurred while producing message", e);
        }
    }



}
