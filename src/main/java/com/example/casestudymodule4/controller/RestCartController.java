package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.dto.CartDto;
import com.example.casestudymodule4.model.Cart;
import com.example.casestudymodule4.model.SkuProduct;
import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.service.ICartService;
import com.example.casestudymodule4.service.ISKProductService;
import com.example.casestudymodule4.service.impl.CartService;
import com.example.casestudymodule4.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin("*")
public class RestCartController {
    @Autowired
    private UserService userService;
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


    @GetMapping("/cart")
    public ResponseEntity<?> Carts(Principal principal) {
        Iterable<CartDto> list;
        if(principal!=null){
            User user = userService.getUserByUserName(principal.getName());
            Integer userId = user.getId();
           list = cartService.findA(userId);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

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

    @GetMapping("/moneyTotal")
    public ResponseEntity<?> moneyTotal(Principal principal) {
        int totalBill=0;
        if(principal!=null){
            User user = userService.getUserByUserName(principal.getName());
            Integer userId = user.getId();

            Iterable<Cart> list = cartService.findCartsByUserId(userId);
            totalBill = cartService.totalBill(list);
            return new ResponseEntity<>(totalBill, HttpStatus.OK);
        }
        return new ResponseEntity<>(totalBill,HttpStatus.OK);
    }

    @GetMapping("/moneyElement/")
    public ResponseEntity<?> moneyElement(Principal principal) {
        Integer elementBill=0;
        if (principal != null) {
            User user = userService.getUserByUserName(principal.getName());
            Integer userId = user.getId();
            Iterable<Cart> list = cartService.findCartsByUserId(userId);
             elementBill = cartService.elementBill(list);
            return new ResponseEntity<>(elementBill, HttpStatus.OK);
        }
        return new ResponseEntity<>(elementBill,HttpStatus.OK);
    }

    @GetMapping("/quantity/{size}/{cartId}")
    public ResponseEntity<?> getQuantity(@PathVariable("size") String size, @PathVariable("cartId") Integer cartId) {
        SkuProduct skuProduct = iskProductService.findSkuProductByIdEqualsAndSizeEquals(cartId, size);
        Integer quantity = skuProduct.getQuantity();
        return new ResponseEntity<>(quantity, HttpStatus.OK);
    }
    @GetMapping("/quantity/{cartId}")
    public ResponseEntity<?> getQuantitySku(@PathVariable("cartId") Integer cartId) {
        Cart cart = cartService.findCartById(cartId);
        Integer quantitySku = 0;
        if(cart.getId()==null){
            return new ResponseEntity<>(quantitySku ,HttpStatus.NO_CONTENT);
        }
        Integer skuId = cart.getSku().getId();
        SkuProduct skuProduct = iskProductService.findById(skuId);
         quantitySku = skuProduct.getQuantity();
        return new ResponseEntity<>(quantitySku, HttpStatus.OK);
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
