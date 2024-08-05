package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.service.IUserService;
import com.example.casestudymodule4.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
public class Cart {
    @Autowired
    private IUserService userService;

    @GetMapping("")
    private String showCart(@RequestParam("id") Long id,Model model) {
        User user = userService.findById(id);
        model.addAttribute("user",user);

        return "/cart";
    }
}
