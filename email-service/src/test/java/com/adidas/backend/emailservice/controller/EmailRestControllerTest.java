package com.adidas.backend.emailservice.controller;

import com.adidas.backend.emailservice.service.sendEmail.SendEmailService;
import com.adidas.backend.emailservice.service.sendEmail.impl.SendEmailServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = EmailRestController.class)
public class EmailRestControllerTest {

    @Autowired
    private EmailRestController emailRestController;

    @MockBean
    private SendEmailServiceImpl sendEmailServiceImpl;

    @Test
    void testSendConfirmationEmailOk() {
        when(sendEmailServiceImpl.sendEmail(anyString())).thenReturn("Hi example@adiClub.com, you have been one of the few customers selected for the new sale!");

        ResponseEntity<String> responseEntity = emailRestController.sendConfirmationEmail("");

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
