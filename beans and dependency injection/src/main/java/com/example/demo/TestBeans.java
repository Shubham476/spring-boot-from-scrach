package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class TestBeans implements TestBeansWithConstructor {
    public void sendEmail(String msg){
        System.out.println("Email Sending..."+ msg);
    }

    @Override
    public void executeOrder(String msg) {
        System.out.println("Order Execution Complete..."+ msg);
    }
}



