package com.alkemy.ong.domain.email;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final EmailGateway emailGateway;

    public EmailService(EmailGateway emailGateway){
        this.emailGateway = emailGateway;
    }

    public String sendMail(String to, String subject, String body){return emailGateway.sendmail(to,subject, body);}
}
