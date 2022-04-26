package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.email.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }
    public ResponseEntity<String> sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body){
        String email = emailService.sendMail(to, subject, body);
        return ResponseEntity.ok("Email sent successfully");
    }
}
