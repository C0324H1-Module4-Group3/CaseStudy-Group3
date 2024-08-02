package com.example.casestudymodule4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SecurityController {
    @GetMapping("/login")
    public String showPageLogin(){
//    public String showPageLogin(@CookieValue(value = "username", defaultValue = "")String username,
//                                @CookieValue(value = "password", defaultValue = "") String password,
//                                @RequestParam(value = "error", defaultValue = "")String error,
//                                Model model) {
//        System.out.println(username);
//        System.out.println(password);
//        model.addAttribute("username", username);
//        model.addAttribute("password", password);
//        model.addAttribute("error", error);
        return "security/login";
    }
}
