package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.dto.OrderDetailDTO;
import com.example.casestudymodule4.model.Order;
import com.example.casestudymodule4.repository.IOrderRepository;
import com.example.casestudymodule4.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;



    @Override
    public Page<OrderDetailDTO> findAllByDate(String startDate, String endDate, Pageable pageable) {
        return orderRepository.findAllByDate(startDate, endDate, pageable);
    }

    @Override
    public Iterable<OrderDetailDTO> getAll() {
        return orderRepository.getOrderDetailDTO();
    }


    @Override
    public List<OrderDetailDTO> findAll() {
        return List.of();
    }

    @Override
    public void save(OrderDetailDTO orderDTO) {

    }

    @Override
    public Page<OrderDetailDTO> findAll(String name, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDetailDTO findById(Integer id) {
        return null;
    }

    @Override
    public void delete(OrderDetailDTO orderDTO) {

    }

    @Override
    public void update(OrderDetailDTO orderDTO) {

    }
}