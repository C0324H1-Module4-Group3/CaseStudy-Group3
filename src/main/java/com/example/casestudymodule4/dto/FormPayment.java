package com.example.casestudymodule4.dto;

import com.example.casestudymodule4.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
@Getter
@Setter
public class FormPayment {
    private Integer userId;
    private Float totalMoney;
    private String code;
    private String status;
    private LocalDate booking_date;
    private String delivery_address;
    private LocalDate delivery_date;
    private String Payment_method;


}
