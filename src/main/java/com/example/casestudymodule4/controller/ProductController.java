package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.Category;
import com.example.casestudymodule4.model.Product;
import com.example.casestudymodule4.model.SkuProduct;
import com.example.casestudymodule4.service.ICategoryService;
import com.example.casestudymodule4.service.IProductService;
import com.example.casestudymodule4.service.ISKProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class ProductController {

    private final IProductService productService;
    private final ISKProductService iskProductService;
    private final ICategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> listCategories() {
        return categoryService.findAll();
    }

    @GetMapping
    public String home() {
        return "/index";
    }

    @GetMapping("/shop")
    public String shop(Model model,
                       @PageableDefault(sort = "id", size = 8, direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Product> products = productService.fetchProducts(pageable);
        int totalPages = products.getTotalPages();
        int currentPage = products.getNumber();
        int startPage = Math.max(0, currentPage - 2);
        int endPage = Math.min(totalPages - 1, currentPage + 2);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/shop";
    }

    @GetMapping("/shop/{id}")
    public String getProductByCategory(Model model,
                                       @PathVariable Integer id,
                                       @PageableDefault(sort = "id", size = 4, direction = Sort.Direction.DESC) Pageable pageable) {
        Category category = categoryService.findById(id.longValue());

        Page<Product> products = productService.findProductByCategory(id, pageable);
        int totalPages = products.getTotalPages();
        int currentPage = products.getNumber();
        int startPage = Math.max(0, currentPage - 2);
        int endPage = Math.min(totalPages - 1, currentPage + 2);

        model.addAttribute("category", category);
        model.addAttribute("products", products);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/shop-category";
    }

    @GetMapping("/search")
    public String searchProducts(Model model,
                                 @RequestParam("searchName") String searchName,
                                 @PageableDefault(sort = "id", size = 4, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Product> products = productService.searchProducts(searchName, pageable);
        int totalPages = products.getTotalPages();
        int currentPage = products.getNumber();
        int startPage = Math.max(0, currentPage - 2);
        int endPage = Math.min(totalPages - 1, currentPage + 2);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "shop";
    }


    @GetMapping("/shop-single/{id}")
    public String getProduct(@PathVariable("id") Integer id, Model model) {
        SkuProduct skuProduct = iskProductService.findById(id);
        if (skuProduct != null) {
            model.addAttribute("skuProduct", skuProduct);
            model.addAttribute("product", skuProduct.getProduct());
        } else {
            // Handle the case where skuProduct is not found
            return "error";
        }
        return "shop-single";
    }
}
