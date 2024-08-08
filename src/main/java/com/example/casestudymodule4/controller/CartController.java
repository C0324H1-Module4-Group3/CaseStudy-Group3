package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.dto.FormPayment;
import com.example.casestudymodule4.model.Cart;
import com.example.casestudymodule4.model.SkuProduct;
import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.service.ISKProductService;
import com.example.casestudymodule4.service.IUserService;
import com.example.casestudymodule4.service.impl.CartService;
import com.example.casestudymodule4.service.impl.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    private ISKProductService skProductService;


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
    @PostMapping("/add")

    public String addToCart(@RequestParam("skuProductId") Integer skuProductId,
                            @RequestParam("quantity") Integer quantity,
                            HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof UserDetails)) {
            return "redirect:/login?redirectTo=/cart/add";
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.getUserByUserName(userDetails.getUsername());

        cartService.addToCart(user, skuProductId, quantity);

        return "redirect:/cart";
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


