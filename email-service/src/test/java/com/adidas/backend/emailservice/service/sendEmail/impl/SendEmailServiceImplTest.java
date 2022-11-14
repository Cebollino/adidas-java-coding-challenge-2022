package com.adidas.backend.emailservice.service.sendEmail.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SendEmailServiceImplTest {
    private SendEmailServiceImpl sendEmailService;

    @BeforeEach
    void setUp() {
        sendEmailService = new SendEmailServiceImpl();
    }

    @Test
    void sendEmailServiceTestOk() {
        String response = sendEmailService.sendEmail("example@adiClub.com");

        Assertions.assertEquals("Hi example@adiClub.com, you have been one of the few customers selected for the new sale!", response);
    }
}
