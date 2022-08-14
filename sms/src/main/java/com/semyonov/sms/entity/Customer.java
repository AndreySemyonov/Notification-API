package com.semyonov.sms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Customer {

    private int id;
    private String name;
    private String email;
    private String phoneNumber;

    private String message;

    public Customer(int id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}

