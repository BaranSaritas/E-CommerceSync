package com.E_CommerceSync.E_CommerceSync.config.kafka;


import com.E_CommerceSync.E_CommerceSync.dto.request.OrderRequest;
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

    /*
    @Scheduled(fixedRate = 1000)
    public void produceModel() {
        try {

            SModel sModel = new SModel(212,"Baran","Sepet");

            String topicModel = "model-topic";
            ProducerRecord<String, SModel> record = new ProducerRecord<String, SModel>(topicModel, sModel);
            producer.send(record);


            logger.info("Sent message: {}", record.toString());
        } catch (Exception e) {
            logger.error("Error occurred while producing message", e);
        }
    }
    */


}
