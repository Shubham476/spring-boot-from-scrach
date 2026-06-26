package com.example.demo.impl;

import com.example.demo.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
//@Qualifier("emailnotif")
@ConditionalOnProperty(name = "notifcation.type", havingValue = "email")
public class EmailNotificationService implements NotificationService {
    @Override
    public void send(String msg) {
        System.out.println("email sending..."+ msg);
    }
}
