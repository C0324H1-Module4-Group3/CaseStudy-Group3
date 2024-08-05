package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.Product;
import com.example.casestudymodule4.service.IProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class RestProductController {
    @Autowired
    private IProductService productService;
    @GetMapping
    public ResponseEntity<?> getProductsByCategoryId(@RequestParam(defaultValue = "1") Integer categoryId) {
        List<Product> products = productService.findProductByCategoryId(categoryId);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
