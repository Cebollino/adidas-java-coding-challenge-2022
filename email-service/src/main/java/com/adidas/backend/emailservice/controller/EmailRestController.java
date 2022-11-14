package com.adidas.backend.emailservice.controller;

import com.adidas.backend.emailservice.service.sendEmail.impl.SendEmailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailRestController {

    private final Logger logger = LoggerFactory.getLogger(EmailRestController.class);
    private final SendEmailServiceImpl emailService;

    public EmailRestController(SendEmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/sendEmail")
    public ResponseEntity<String> sendConfirmationEmail(
            @RequestParam(value = "emailAddress") final String emailAddress) {
        String emailServiceResponse = emailService.sendEmail(emailAddress);

        if(logger.isDebugEnabled()) logger.debug(emailServiceResponse);

        return ResponseEntity.ok().body(null);
    }
}
