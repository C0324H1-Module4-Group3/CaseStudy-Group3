package com.example.casestudymodule4.service;

import com.example.casestudymodule4.dto.OrderDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService extends IGenerateService<OrderDetailDTO> {
    Page<OrderDetailDTO> findAllByDate(String startDate, String endDate, Pageable pageable);
    Iterable<OrderDetailDTO> getAll();
}
