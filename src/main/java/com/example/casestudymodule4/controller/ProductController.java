package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.Product;
import com.example.casestudymodule4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class ProductController {
    @Autowired
    private IProductService productService;
    @GetMapping
    public String home() {
        return "/index";
    }
    @GetMapping("/shop")
    public String shop() {
        return "/shop";
    }
//    @GetMapping("/shop-single")
//    public String shopSingle() {
//        return "/shop-single";
//    }
    @GetMapping("/shop-single")
    public String productId(@RequestParam Integer productId, Model model) {
        Product product = productService.findProductById(productId);
        model.addAttribute("product", product);
        System.out.println(product.toString());
        return "/shop-single";
    }

}
