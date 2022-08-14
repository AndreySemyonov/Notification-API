package com.semyonov.email.kafka;


import com.semyonov.email.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private KafkaTemplate<String, Customer> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Customer> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Customer data){
        Message<Customer> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "email-topic")
                .build();
        kafkaTemplate.send(message);
        LOGGER.info("Email was sent");
    }

}
