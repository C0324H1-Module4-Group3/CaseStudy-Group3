package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.SkuProduct;
import com.example.casestudymodule4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminProductController {
    @Autowired
    private IProductService productService;
    @GetMapping("/admin")
    public String adminPage(){
        return "admin/index";
    }
    @GetMapping("/manager")
    public String managerPage(Model model, Pageable pageable) {
        Page<SkuProduct> skuProducts = productService.findAll(pageable);
        model.addAttribute("skuProducts", skuProducts);
        return "admin/manager";
    }
}
