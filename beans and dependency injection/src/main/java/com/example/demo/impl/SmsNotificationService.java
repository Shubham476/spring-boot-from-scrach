package com.example.demo.impl;

import com.example.demo.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("smsnotif")
public class SmsNotificationService implements NotificationService {
    @Override
    public void send(String msg) {
        System.out.println("sms sending.."+ msg);
    }
}
