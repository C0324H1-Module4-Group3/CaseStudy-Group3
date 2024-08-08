package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.dto.CartDto;
import com.example.casestudymodule4.model.Cart;
import com.example.casestudymodule4.model.SkuProduct;
import com.example.casestudymodule4.service.ICartService;
import com.example.casestudymodule4.service.ISKProductService;
import com.example.casestudymodule4.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin("*")
public class RestCartController {
    @Autowired
    private ICartService cartService;
    @Autowired
    private ISKProductService iskProductService;

    @GetMapping("")
    public ResponseEntity<?> listCarts() {
        Iterable<Cart> list = cartService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> Cart(@PathVariable Integer id) {
        Iterable<Cart> list = cartService.findCartByUserId(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> Carts(@PathVariable Integer userId) {
        Iterable<CartDto> list = cartService.findA(userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PostMapping("/addQuantity/{cartId}")
    public ResponseEntity<?> addQuantity(@PathVariable("cartId") Integer cartId) {
        cartService.addQuantity(cartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/minusQuantity/{cartId}")
    public ResponseEntity<?> minusQuantity(@PathVariable("cartId") Integer cartId) {
        cartService.minusQuantity(cartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/moneyTotal/{userId}")
    public ResponseEntity<?> moneyTotal(@PathVariable("userId") Integer userId) {
        Iterable<Cart> list = cartService.findCartsByUserId(userId);
        Integer totalBill = cartService.totalBill(list);
        return new ResponseEntity<>(totalBill, HttpStatus.OK);
    }

    @GetMapping("/moneyElement/{userId}")
    public ResponseEntity<?> moneyElement(@PathVariable("userId") Integer userId) {
        Iterable<Cart> list = cartService.findCartsByUserId(userId);
        Integer elementBill = cartService.elementBill(list);
        return new ResponseEntity<>(elementBill, HttpStatus.OK);
    }

    @GetMapping("/quantity/{size}/{cartId}")
    public ResponseEntity<?> getQuantity(@PathVariable("size") String size, @PathVariable("cartId") Integer cartId) {
        SkuProduct skuProduct = iskProductService.findSkuProductByIdEqualsAndSizeEquals(cartId, size);
        Integer quantity = skuProduct.getQuantity();
        return new ResponseEntity<>(quantity, HttpStatus.OK);
    }



    @PutMapping("/update/{cartId}")
    public ResponseEntity<?> findSku(@PathVariable("cartId") Integer cartId, @RequestParam("size") String size) {
        Integer quantity = 0;
        SkuProduct skuProduct = cartService.findSkuProductByProductIdAndSize(cartId, size);
        if (skuProduct != null) {
            quantity = skuProduct.getQuantity();
            cartService.updateCart(cartId, size);
        }
        return new ResponseEntity<>(quantity, HttpStatus.OK);
    }

}
