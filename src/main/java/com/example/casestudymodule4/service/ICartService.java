package com.example.casestudymodule4.service;

import com.example.casestudymodule4.model.Cart;

import java.util.List;

public interface ICartService {


    List<Cart> findCartsByUserId(Integer userId);

    void delete(Integer cartId);

    int totalBill(Iterable<Cart> carts);

    Iterable<Cart> findCartByUserId(int i);

    Cart findCartById(Integer cartId);

    void addQuantity(Integer cartId);

    void minusQuantity(Integer cartId);

    Iterable<Cart> findAll();
}
