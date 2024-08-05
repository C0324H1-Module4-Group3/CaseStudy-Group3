package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.Cart;
import com.example.casestudymodule4.service.ICartService;
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


    @PostMapping("/addQuantity/{cartId}")
    public ResponseEntity<?> addQuantity(@PathVariable("cartId") Integer cartId ){
        cartService.addQuantity(cartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/minusQuantity/{cartId}")
    public ResponseEntity<?> minusQuantity(@PathVariable("cartId") Integer cartId ){
        cartService.minusQuantity(cartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> listCart(@PathVariable Integer id){
      Iterable<Cart> list =  cartService.findCartByUserId(id);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> listCart(){
        Iterable<Cart> list =  cartService.findAll();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
 }
