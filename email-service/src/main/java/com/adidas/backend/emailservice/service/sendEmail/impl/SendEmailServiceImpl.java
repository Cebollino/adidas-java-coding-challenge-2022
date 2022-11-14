package com.adidas.backend.emailservice.service.sendEmail.impl;

import com.adidas.backend.emailservice.service.sendEmail.SendEmailService;
import org.springframework.stereotype.Service;

@Service
public class SendEmailServiceImpl implements SendEmailService {
    @Override
    public String sendEmail(String emailAddress) {
        return "Hi "+emailAddress+", you have been one of the few customers selected for the new sale!";
    }
}
