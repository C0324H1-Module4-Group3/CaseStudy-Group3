package com.example.casestudymodule4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class ProductController {
    @GetMapping
    public String home() {
        return "/index";
    }
    @GetMapping("/shop")
    public String shop() {
        return "/shop";
    }
    @GetMapping("/shop-single")
    public String shopSingle() {
        return "/shop-single";
    }
}
