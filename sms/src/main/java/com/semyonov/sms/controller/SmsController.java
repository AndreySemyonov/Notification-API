package com.semyonov.sms.controller;

import com.semyonov.sms.entity.Customer;
import com.semyonov.sms.kafka.KafkaProducer;
import com.semyonov.sms.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class SmsController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    KafkaProducer kafkaProducer;

    @RequestMapping(value = "/sms/{customerId}", method = RequestMethod.POST)
    public ResponseEntity<String> sendSms(@PathVariable("customerId") int customerId, @RequestBody String text){

        try {

            Customer customerJson = customerRepository.getCustomerById(customerId);
            customerJson.setMessage(text);
            kafkaProducer.sendMessage(customerJson);

            return ResponseEntity.ok("Message was sent to "+customerJson.getName());

        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Sms wasn't sent. Wrong id.");
        }
    }
}
