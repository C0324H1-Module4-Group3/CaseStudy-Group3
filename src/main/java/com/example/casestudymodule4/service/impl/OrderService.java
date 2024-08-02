package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.Order;
import com.example.casestudymodule4.service.IOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class OrderService implements IOrderService {
    @Override
    public List<Order> findAll() {
        return List.of();
    }

    @Override
    public void save(Order order) {

    }

    @Override
    public Page<Order> findAll(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public void update(Order order) {

    }
}
