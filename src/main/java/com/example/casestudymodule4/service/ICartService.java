package com.example.casestudymodule4.service;

import com.example.casestudymodule4.model.Cart;

import java.util.List;

public interface ICartService {


    List<Cart> findCartsByUserId(Integer userId);

    void delete(Integer cartId);

    int totalBill(List<Cart> carts);
}
