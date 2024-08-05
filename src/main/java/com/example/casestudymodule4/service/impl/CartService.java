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
    public int totalBill(Iterable<Cart> carts) {
        int totalBills = 0;
        for (Cart items : carts) {
            totalBills += items.getQuantity() * items.getSku().getPrice();
        }
        return totalBills;
    }

    @Override
    public void addQuantity(Integer cartId) {
        Cart cart = this.findCartById(cartId);
        if (cart.getSku().getQuantity() >= cart.getQuantity() + 1) {
            cart.setQuantity(cart.getQuantity() + 1);
        }
        this.cartRepo.save(cart);
    }
    @Override
    public void minusQuantity(Integer cartId) {
        Cart cart = this.findCartById(cartId);
        cart.setQuantity(cart.getQuantity() - 1);
        if (cart.getQuantity()<1){
            cart.setQuantity(1);
        }
        this.cartRepo.save(cart);
    }

    @Override
    public Iterable<Cart> findAll() {
        return cartRepo.findAll();
    }


    @Override
    public Iterable<Cart> findCartByUserId(int id) {
        return cartRepo.findCartsByUserId(id);
    }

    @Override
    public Cart findCartById(Integer cartId) {
        return this.cartRepo.findById(cartId).orElse(new Cart());
    }


}
