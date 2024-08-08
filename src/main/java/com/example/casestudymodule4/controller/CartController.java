package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.dto.FormPayment;
import com.example.casestudymodule4.model.Cart;
import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.service.IUserService;
import com.example.casestudymodule4.service.impl.CartService;
import com.example.casestudymodule4.service.impl.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @GetMapping("")
    private String showCart(@RequestParam("id") Integer userid, Model model) {

        Iterable<Cart> carts = cartService.findCartsByUserId(userid);

        int totalBill = cartService.totalBill(carts);
        String code = cartService.getRandomCode();
        User user = userService.findById(userid);

        model.addAttribute("user", user);
        model.addAttribute("code", code);
        model.addAttribute("cart", carts);
        model.addAttribute("payment", new FormPayment());
        model.addAttribute("total", totalBill);
        model.addAttribute("userId", userid);
        return "/cart";
    }

//    @GetMapping("")
//    private String showCart( Model model, Principal principal) {
//
//            Integer userId = userService.getUserByUserName(principal.getName()).getId();
//            String name = userService.getUserByUserName(principal.getName()).getName();
//            model.addAttribute("userId", userId);
//            model.addAttribute("userName", name);
//
//            Iterable<Cart> carts = cartService.findCartsByUserId(userId);
//            model.addAttribute("cart", carts);
//            int totalBill = cartService.totalBill(carts);
//            model.addAttribute("total", totalBill);
//            model.addAttribute("payment",new FormPayment());
//            return "/cart";
//
//    }

    @GetMapping("/delete/{id}")
    private String deleteCart(@PathVariable("id") Integer cartId) {
        cartService.delete(cartId);
        return "redirect:/cart?id=1";
    }



    @PostMapping("/create")
    private String save(@ModelAttribute("payment") FormPayment formPayment) {
        cartService.save(formPayment);
        return "redirect:/cart?id=1";
    }



//    @PostMapping("/create")
//    private String save(@ModelAttribute("payment") FormPayment formPayment) {
//        cartService.save(formPayment);
//        return "redirect:/payment/submitOrder";
//    }

//    @PostMapping("/add/{skuId")
//    private String addToCart(@PathVariable("skuId") Integer skuId,
//                             @RequestParam())


}
