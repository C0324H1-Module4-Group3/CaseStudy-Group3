package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.Product;
import com.example.casestudymodule4.model.SkuProduct;
import com.example.casestudymodule4.service.IProductService;
import com.example.casestudymodule4.service.ISKProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminProductController {
    @Autowired
    private ISKProductService skproductService;
    @Autowired
    private IProductService productService;

    @GetMapping()
    public String adminPage() {
        return "admin/index";
    }

    @GetMapping("/manager")
    public String managerPage(Model model, @RequestParam(required = false) String keyword) {
        List<Product> products;
        List<SkuProduct> skuProducts;

        if (keyword != null && !keyword.isEmpty()) {
            products = productService.searchProducts(keyword);
            skuProducts = skproductService.searchSkuProducts(keyword);
        } else {
            products = productService.findAll();
            skuProducts = skproductService.findAll();
        }

        model.addAttribute("skuProducts", skuProducts);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "admin/manager";
    }

    @GetMapping("create")
    public String showCreateForm(Model model) {
        SkuProduct skuProduct = new SkuProduct();
        List<Product> products = productService.findAll();
        model.addAttribute("skuProduct", skuProduct);
        model.addAttribute("products", products);
        return "admin/create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("skuProduct") SkuProduct skuProduct) {
        Product product = productService.findProductById(skuProduct.getProduct().getId());
        skuProduct.setProduct(product);
        skproductService.save(skuProduct);
        return "redirect:/admin/manager";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id, RedirectAttributes redirect) {
        skproductService.remove(id);
        redirect.addFlashAttribute("message", "Xóa sản phẩm thành công");
        return "redirect:/admin/manager";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Integer id, Model model) {
        List<Product> products = productService.findAll();
        SkuProduct skuProduct = skproductService.findById(id);
        model.addAttribute("products", products);
        model.addAttribute("skuProduct", skuProduct);
        return "admin/update";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@ModelAttribute("skuProduct") SkuProduct skuProduct, RedirectAttributes redirect) {
        skproductService.save(skuProduct);
        redirect.addFlashAttribute("message", "Cập nhật sản phẩm thành công");
        return "redirect:/admin/manager";
    }
}
