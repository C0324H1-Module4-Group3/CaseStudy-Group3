package com.example.casestudymodule4.service;

import com.example.casestudymodule4.dto.CartDto;
import com.example.casestudymodule4.dto.FormPayment;
import com.example.casestudymodule4.model.Cart;
import com.example.casestudymodule4.model.SkuProduct;
import com.example.casestudymodule4.model.User;

import java.util.List;

public interface ICartService {

    void deleteCart();
    Iterable<Cart> findCartsByUserId(Integer userId);

    void delete(Integer cartId);

    int totalBill(Iterable<Cart> carts);

    Iterable<Cart> findCartByUserId(int i);

    Cart findCartById(Integer cartId);

    void addQuantity(Integer cartId);

    void minusQuantity(Integer cartId);

    Iterable<Cart> findAll();

    void updateCart(Integer cartId, String size);

    Integer elementBill(Iterable<Cart> list);

    Iterable<CartDto> findA(Integer id);

    String getRandomCode();

    void save(FormPayment formPayment);

    void addToCart(User user, Integer skuProductId, Integer quantity);

}
