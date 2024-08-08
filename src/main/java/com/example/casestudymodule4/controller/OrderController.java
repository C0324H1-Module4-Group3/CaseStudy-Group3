package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.dto.MonthlyRevenueDTO;
import com.example.casestudymodule4.dto.OrderDetailDTO;
import com.example.casestudymodule4.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/order")
    public String listOrdersDate(Model model,
                                 @RequestParam(value = "startDate", defaultValue = "LocalDate.now") String startDate,
                                 @RequestParam(value = "endDate", defaultValue = "LocalDate.now") String endDate,
                                 @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<OrderDetailDTO> orderDetailDTOs = orderService.findAllByDate(startDate, endDate, PageRequest.of(page, 5));
        model.addAttribute("orderDetailDTOs", orderDetailDTOs);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "manager-order";
    }

}
