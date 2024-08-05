package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.Cart;
import com.example.casestudymodule4.repository.CartRepo;
import com.example.casestudymodule4.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepo cartRepo;

    public List<Cart> findCartsByUserId(Integer userId) {
        return cartRepo.findCartsByUserId(userId);
    }

    @Override
    public void delete(Integer cartId) {
        cartRepo.deleteById(cartId);
    }

    @Override
    public int totalBill(List<Cart> carts) {
        int totalBills = 0;
        for (Cart items : carts) {
            totalBills += items.getQuantity() * items.getSku().getPrice();
        }
        return totalBills;
    }
}
