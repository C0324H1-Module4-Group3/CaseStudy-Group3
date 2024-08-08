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


//    @GetMapping("")
//    private String showCart(@RequestParam("id") Integer userid, Model model) {
//
//        Iterable<Cart> carts = cartService.findCartsByUserId(userid);
////
//        int totalBill = cartService.totalBill(carts);
//        String code = cartService.getRandomCode();
//        User user = userService.findById(userid);
//
//        model.addAttribute("user", user);
//        model.addAttribute("code", code);
//        model.addAttribute("cart", carts);
//        model.addAttribute("payment", new FormPayment());
//        model.addAttribute("total", totalBill);
//        model.addAttribute("userId", userid);
//        return "/cart";
//    }

    @GetMapping("")
    private String showCart(Model model, Principal principal) {

        User user = userService.getUserByUserName(principal.getName());
        Integer userId = user.getId();
        String userName = user.getName();

        Iterable<Cart> carts = cartService.findCartsByUserId(userId);
        int totalBill = cartService.totalBill(carts);
        String code = cartService.getRandomCode();


        model.addAttribute("user", user);
        model.addAttribute("code", code);
        model.addAttribute("cart", carts);
        model.addAttribute("payment", new FormPayment());
        model.addAttribute("total", totalBill);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        return "/cart";

    }

    @GetMapping("/delete/{cardId}")
    private String deleteCart(@PathVariable("cardId") Integer cartId) {
        cartService.delete(cartId);
        return "redirect:/cart";
    }


    @PostMapping("/create")
    private String save(@ModelAttribute("payment") FormPayment formPayment) {
        cartService.save(formPayment);
        return "redirect:/cart";
    }

//    @PostMapping("/add")
//    public String addToCart(@RequestParam("skuProductId") Integer skuProductId,
//                            @RequestParam("quantity") Integer quantity,
//                            HttpServletRequest request) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof UserDetails)) {
//            return "redirect:/login?redirectTo=/cart/add";
//        }
//
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        User user = userService.getUserByUserName(userDetails.getUsername());
//
//        cartService.addToCart(user, skuProductId, quantity);
//
//        return "redirect:/cart";
//    }
@PostMapping("/add")
public String addToCart(@RequestParam("skuProductId") Integer skuProductId,
                        @RequestParam("quantity") Integer quantity,
                        HttpServletRequest request) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof UserDetails)) {
        HttpSession session = request.getSession();
        session.setAttribute("redirectAfterLogin", "/cart"); // Store the desired redirect URL
        return "redirect:/login";
    }

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    User user = userService.getUserByUserName(userDetails.getUsername());

    cartService.addToCart(user, skuProductId, quantity);

    return "redirect:/cart";
}



}


