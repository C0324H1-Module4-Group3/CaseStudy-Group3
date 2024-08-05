package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.model.VerificationToken;
import com.example.casestudymodule4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class VerificationController {
    @Autowired
    private IUserService userService;
    @GetMapping("/verify")
    public String verifyAccount(@RequestParam("token") String token) {
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            return "Invalid token";
        }
        if (verificationToken.getExpiryTime().isBefore(LocalDateTime.now())) {
            return "Expired token";
        }
        User user = verificationToken.getUser();
        user.setEnable(true);
        userService.verifyUser(user);
        return "Account verified successfully";
    }
}