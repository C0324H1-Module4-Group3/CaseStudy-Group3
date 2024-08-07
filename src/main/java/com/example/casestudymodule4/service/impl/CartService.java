package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.dto.CartDto;
import com.example.casestudymodule4.dto.FormPayment;
import com.example.casestudymodule4.model.*;
import com.example.casestudymodule4.repository.CartRepo;
import com.example.casestudymodule4.repository.IOrderRepository;
import com.example.casestudymodule4.repository.ISKProductRepository;
import com.example.casestudymodule4.repository.IUserRepository;
import com.example.casestudymodule4.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CartService implements ICartService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private SKProductService skProductService;
    @Autowired
    private ISKProductRepository iskProductRepository;


    public Iterable<Cart> findCartsByUserId(Integer userId) {
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
    public Integer elementBill(Iterable<Cart> list) {

        return null;
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
        if (cart.getQuantity() < 1) {
            cart.setQuantity(1);
        }
        this.cartRepo.save(cart);
    }

    @Override
    public Iterable<Cart> findAll() {
        return cartRepo.findAll();
    }

    @Override
    public void updateCart(Integer cartId, String size) {
        Cart cart = findCartById(cartId);
        Integer productId = cart.getSku().getProduct().getId();
        SkuProduct skuProduct = iskProductRepository.findSkuProductByProductIdAndSize(productId,size);
        cart.setSku(skuProduct);
        cart.setQuantity(cart.getQuantity());
        cart.setCustomer(cart.getCustomer());
        cart.setId(cart.getId());
        cartRepo.save(cart);
    }

    @Override
    public Iterable<Cart> findCartByUserId(int id) {
        return cartRepo.findCartsByUserId(id);
    }
    public Iterable<CartDto> findA(Integer id) {
        return cartRepo.findAllByCustomerId(id);
    }

    @Override
    public Cart findCartById(Integer cartId) {
        return this.cartRepo.findById(cartId).orElse(new Cart());
    }

    public String getRandomCode() {
        int randomIntegerInRange = ThreadLocalRandom.current().nextInt(100, 999);
        if (randomIntegerInRange > 500) {
            return "GTH-" + randomIntegerInRange;
        }
        return "FTH-" + randomIntegerInRange;
    }

    @Override
    public void save(FormPayment formPayment) {
        Order order = new Order();
        order.setBookingDate(LocalDate.now());
        order.setDeliveryAddress(formPayment.getDelivery_address());
        order.setDeliveryDate(LocalDate.now().plusDays(3));
        order.setPaymentMethod("vnPay");
        order.setTotalMoney(formPayment.getTotalMoney());
        order.setCode(formPayment.getCode());
        order.setStatus("chưa thanh toán");
        User user = userRepository.findById(formPayment.getUserId()).orElse(null);
        order.setCustomer(user);
        orderRepository.save(order);

    }


}
