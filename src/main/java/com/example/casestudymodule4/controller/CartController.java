package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.Cart;
import com.example.casestudymodule4.service.IUserService;
import com.example.casestudymodule4.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private IUserService userService;
    @Autowired
    private CartService cartService;

    @GetMapping("")
    private String showCart(@RequestParam("id") Integer UserId, Model model) {
        List<Cart> carts = cartService.findCartsByUserId(UserId);
        model.addAttribute("cart", carts);
        int totalBill = cartService.totalBill(carts);
        model.addAttribute("total",totalBill);
        return "/cart";
    }
    @GetMapping("/delete/{id}")
    private String deleteCart(@PathVariable("id") Integer cartId){
        cartService.delete(cartId);
        return "redirect:/cart?id=1";
    }
}
