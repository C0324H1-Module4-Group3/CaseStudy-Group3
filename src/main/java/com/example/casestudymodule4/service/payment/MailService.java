package com.example.casestudymodule4.service.payment;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MailService {
    private static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=\"utf-8\"";

    @Value("smtp.gmail.com")
    private String host;
    @Value("587")
    private String port;
    @Value("mutdshop@gmail.com")
    private String email;
    @Value("vdtkhmqeouxlfzlc")
    private String password;

    @Autowired
    ThymeleafService thymeleafService;

    public String sendMail(String oderInfo,String paymentTime,String transactionId,String totalPrice) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
        Message message = new MimeMessage(session);
        try {
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("phongton219@gmail.com")});

            message.setFrom(new InternetAddress(email));
            message.setSubject("Spring-email-with-thymeleaf subject");
            message.setContent(thymeleafService.getContent(oderInfo,paymentTime,transactionId,totalPrice), CONTENT_TYPE_TEXT_HTML);
            Transport.send(message);
            return "Email sent successfully";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Email sent failed";
        }

    }
}
