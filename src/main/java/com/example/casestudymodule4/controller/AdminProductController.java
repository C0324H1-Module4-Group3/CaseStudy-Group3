package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.dto.MonthlyRevenueDTO;
import com.example.casestudymodule4.model.Product;
import com.example.casestudymodule4.model.SkuProduct;
import com.example.casestudymodule4.service.IOrderService;
import com.example.casestudymodule4.service.IProductService;
import com.example.casestudymodule4.service.ISKProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("/admin")
@Controller
public class AdminProductController {
    @Autowired
    private ISKProductService skproductService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IOrderService orderService;

    @GetMapping("")
    public String adminPage(Model model,
                            @RequestParam(value = "year", defaultValue = "2024") int year) {
        List<MonthlyRevenueDTO> revenues = orderService.getMonthlyRevenueByYear(year);
        model.addAttribute("revenues", revenues);
        model.addAttribute("year", year);
        return "admin/index";
    }

    @GetMapping("/manager")
    public String managerPage(Model model, @RequestParam(required = false) String keyword,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size) {
        Page<Product> productPage;
        Page<SkuProduct> skuProductPage;
        Pageable pageable = PageRequest.of(page, size);

        if (keyword != null && !keyword.isEmpty()) {
            productPage = productService.searchProducts(keyword, pageable);
            skuProductPage = skproductService.searchSkuProducts(keyword, pageable);
        } else {
            productPage = productService.findAll(pageable);
            skuProductPage = skproductService.findAll(pageable);
        }

        model.addAttribute("skuProducts", skuProductPage);
        model.addAttribute("products", productPage);
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
    public String createProduct(@Validated @ModelAttribute("skuProduct") SkuProduct skuProduct,
                                BindingResult result,Model model) {
        if (result.hasErrors()){
            List<Product> products = productService.findAll();
            model.addAttribute("products", products);
            return "admin/create";
        }
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
