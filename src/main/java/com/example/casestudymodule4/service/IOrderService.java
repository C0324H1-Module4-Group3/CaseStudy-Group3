package com.example.casestudymodule4.service;

import com.example.casestudymodule4.dto.MonthlyRevenueDTO;
import com.example.casestudymodule4.dto.OrderDetailDTO;
import com.example.casestudymodule4.model.Order;
import com.example.casestudymodule4.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService extends IGenerateService<OrderDetailDTO> {
    Page<OrderDetailDTO> findAllByDate(String startDate, String endDate, Pageable pageable);
    Iterable<OrderDetailDTO> getAll();

    List<MonthlyRevenueDTO> getMonthlyRevenueByYear(int year);
    Order findByCode(String code);
    void updateOrderStatus(Order order,String status);
    List<Order> getAllOrder();
    void deleteOrder(String code);
    Order findOrderByCustomerAndStatus(User customer, String status);
    String findStatusByOrderId(Integer orderId);
}
