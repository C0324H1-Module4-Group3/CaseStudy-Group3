package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.Product;
import com.example.casestudymodule4.service.IProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public String shop(Model model,
                       @RequestParam("page") Optional<String> pageOptional,
                       HttpServletRequest request) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                // convert from String to int
                page = Integer.parseInt(pageOptional.get());
            } else {
                // page = 1
            }
        } catch (Exception e) {
            // page = 1
            // TODO: handle exception
        }

        Pageable pageable = PageRequest.of(page - 1, 6);
        Page<Product> products = productService.fetchProducts(pageable);
        List<Product> productList = products.getContent().size() > 0 ? products.getContent()
                : new ArrayList<Product>();

        model.addAttribute("products", productList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", products.getTotalPages());

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
