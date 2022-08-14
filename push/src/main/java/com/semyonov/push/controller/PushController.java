package com.semyonov.push.controller;


import com.semyonov.push.entity.Customer;
import com.semyonov.push.kafka.KafkaProducer;
import com.semyonov.push.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class PushController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    KafkaProducer kafkaProducer;

    @RequestMapping(value = "/push/{customerId}", method = RequestMethod.POST)
    public ResponseEntity<String> sendPush(@PathVariable("customerId") int customerId, @RequestBody String text){

        try {

            Customer customerJson = customerRepository.getCustomerById(customerId);
            customerJson.setMessage(text);
            kafkaProducer.sendMessage(customerJson);

            return ResponseEntity.ok("Push was sent to "+customerJson.getName());

        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Push wasn't sent. Wrong id.");
        }
    }
}
