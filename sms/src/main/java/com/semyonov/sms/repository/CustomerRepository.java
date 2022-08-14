package com.semyonov.sms.repository;

import com.semyonov.sms.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class CustomerRepository {

    private static Set<Customer> customers = new HashSet<>();

    static {
        Customer customer1 = new Customer(1,"Alexander", "alex@mail.ru", "+79003001111");
        Customer customer2 = new Customer(2,"Nikolay", "nik@mail.ru", "+79003002222");
        Customer customer3 = new Customer(3,"Irina", "irina@mail.ru", "+79003003333");
        Collections.addAll(customers, customer1, customer2, customer3);
    }

    public Customer getCustomerById(int id) {
        return customers.stream().filter(x -> x.getId() == id).findAny().get();
    }

}
