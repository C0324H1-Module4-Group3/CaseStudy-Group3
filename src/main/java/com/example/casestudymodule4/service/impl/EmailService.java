package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.Order;
import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.model.VerificationToken;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private TemplateEngine templateEngine;
    public void sendVerificationEmail(VerificationToken verificationToken) {
        String subject = "Email verification";
        String confirmationLink = "http://localhost:8080/verify?token=" + verificationToken.getToken();
        String message = "Please click the link below to verify your account in ManUTD Shop: \n "+confirmationLink;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(verificationToken.getUser().getUserName());
        email.setSubject(subject);
        email.setText(message);
        emailSender.send(email);
    }
    public void sendOrderConfirmationEmail(Order order) {
        String subject = "Order confirmation";
        String message = "Please find your order detail in attachment file.\n Thank you, \nManUtd Team.  ";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(order.getCustomer().getUserName());
        email.setSubject(subject);
        email.setText(message);
        emailSender.send(email);
    }

    public void sendEmail(String to, String subject, Map<String, Object> model, String templateName) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);

            String content = templateEngine.process(templateName, new Context(Locale.getDefault(), model));
            helper.setText(content, true);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
