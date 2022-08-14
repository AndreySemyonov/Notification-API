package com.semyonov.email.controller;


import com.semyonov.email.entity.Customer;
import com.semyonov.email.kafka.KafkaProducer;
import com.semyonov.email.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class EmailController {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    KafkaProducer kafkaProducer;

    @RequestMapping(value = "/email/{customerId}", method = RequestMethod.POST)
    public ResponseEntity<String> sendEmail(@PathVariable("customerId") int customerId, @RequestBody String text){

        try {

            Customer customerJson = customerRepository.getCustomerById(customerId);
            customerJson.setMessage(text);
            kafkaProducer.sendMessage(customerJson);

            return ResponseEntity.ok("Email was sent to "+customerJson.getName());

        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Email wasn't sent. Wrong id.");
        }
    }
}
