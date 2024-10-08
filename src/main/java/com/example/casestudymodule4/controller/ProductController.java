package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.dto.ProductResponse;
import com.example.casestudymodule4.model.Category;
import com.example.casestudymodule4.model.Product;
import com.example.casestudymodule4.model.SkuProduct;
import com.example.casestudymodule4.service.ICategoryService;
import com.example.casestudymodule4.service.IProductService;
import com.example.casestudymodule4.service.ISKProductService;
import com.example.casestudymodule4.service.IUserService;
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

import java.security.Principal;
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
    private final IUserService userService;
    @ModelAttribute("categories")
    public List<Category> listCategories() {
        return categoryService.findAll();
    }

    @GetMapping
    public String home(Principal principal, Model model) {
        if (principal != null) {
            String userName = userService.getUserByUserName(principal.getName()).getName();
            model.addAttribute("userName", userName);
        }
        return "/index";
    }

    @GetMapping("/shop")
    public String shop(Model model,
                       @PageableDefault(sort = "id", size = 15, direction = Sort.Direction.DESC) Pageable pageable) {

        Page<ProductResponse> products = productService.fetchProductResponse(pageable);
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
                                       @PageableDefault(sort = "id", size = 15, direction = Sort.Direction.DESC) Pageable pageable) {
        Category category = categoryService.findById(id.longValue());

        Page<ProductResponse> products = productService.findProductByIdCategory(id, pageable);
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
                                 @PageableDefault(sort = "id", size = 15, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ProductResponse> products = productService.findProductByName(searchName, pageable);
        int totalPages = products.getTotalPages();
        int currentPage = products.getNumber();
        int startPage = Math.max(0, currentPage - 2);
        int endPage = Math.min(totalPages - 1, currentPage + 2);

        model.addAttribute("products", products);
        model.addAttribute("name", searchName);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "/shop-search";
    }



    @GetMapping("/shop-single/{id}")
    public String getProduct(@PathVariable("id") Integer id, Model model) {
        SkuProduct skuProduct = iskProductService.findById(id);
        if (skuProduct != null) {
            model.addAttribute("skuProduct", skuProduct);
            model.addAttribute("product", skuProduct.getProduct());
        } else {
            return "/error/error";
        }
        return "shop-single";
    }
    @GetMapping("/contact")
    public String viewContact(){
        return "contact";
    }
}

