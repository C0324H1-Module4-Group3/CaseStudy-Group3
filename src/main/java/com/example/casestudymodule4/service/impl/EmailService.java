package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;
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
}
