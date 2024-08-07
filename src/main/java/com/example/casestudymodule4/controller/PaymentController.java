package com.example.casestudymodule4.controller;


import com.example.casestudymodule4.configure.payment.VNPayService;
import com.example.casestudymodule4.service.payment.MailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

@Controller
public class PaymentController {
    @Autowired
    private VNPayService vnPayService;
    @Autowired
    MailService mailService;



    @GetMapping("")
    public String home(Model model){
        return "index";
    }


    @PostMapping("/submitOrder")
    public String submidOrder(@RequestParam("amount") int orderTotal,
                            @RequestParam("orderInfo") String orderInfo,
                            HttpServletRequest request) throws UnsupportedEncodingException {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
        return "redirect:" + vnpayUrl;
    }

    @GetMapping("/vnpay-payment")
    public ModelAndView GetMapping(HttpServletRequest request){
        int paymentStatus =vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orderId", orderInfo);
        modelAndView.addObject("totalPrice", totalPrice);
        modelAndView.addObject("paymentTime", paymentTime);
        modelAndView.addObject("transactionId", transactionId);
        modelAndView.setViewName("payment/ordersuccess");
        String emailStatus =mailService.sendMail(orderInfo,paymentTime,transactionId,totalPrice);
        modelAndView.addObject("emailStatus", emailStatus);
        return  modelAndView;
    }
}
