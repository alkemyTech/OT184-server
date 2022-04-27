package com.alkemy.ong.data.gateways;

import com.alkemy.ong.domain.email.EmailGateway;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DefaultEmailGateway implements EmailGateway {

    private final SendGrid sendGrid;

    public DefaultEmailGateway(SendGrid sendGrid){
        this.sendGrid = sendGrid;
    }


    @Value("${sendgridProperties.email}")
    private String emailVariable;



    private static final Logger log = LoggerFactory.getLogger(DefaultEmailGateway.class);
    public String sendmail(String to, String subject, String body) {

        Email email = new Email(emailVariable);
        Mail mail = new Mail(email, subject, new Email(to), new Content("text", body));

//        SendGrid sg = new SendGrid(emailApiKey);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            log.info("{}", response.getStatusCode());
            log.info("{}", response.getBody());
            log.info("{}", response.getHeaders());

            return null;

        } catch (IOException ex) {
            log.error("Error trying to send email");
        }

        return null;
    }

}
