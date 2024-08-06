package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.Product;
import com.example.casestudymodule4.model.SkuProduct;
import com.example.casestudymodule4.service.IProductService;
import com.example.casestudymodule4.service.ISKProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ISKProductService iskProductService;
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
    @GetMapping("/shop-single/{id}")
    public String getProductDetail(@PathVariable Integer id, Model model) {
        SkuProduct skuProduct = iskProductService.findById(id);
            model.addAttribute("product", skuProduct);
            return "/shop-single";
    }
    @ExceptionHandler(Exception.class)
    public String handleError (Exception e){
        return "error/400";
    }
}
