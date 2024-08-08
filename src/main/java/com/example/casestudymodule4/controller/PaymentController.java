package com.example.casestudymodule4.controller;


import com.example.casestudymodule4.configure.payment.VNPayService;
import com.example.casestudymodule4.dto.FormPayment;
import com.example.casestudymodule4.model.*;
import com.example.casestudymodule4.service.ICartService;
import com.example.casestudymodule4.service.IOrderService;
import com.example.casestudymodule4.service.ISKProductService;
import com.example.casestudymodule4.service.IUserService;
import com.example.casestudymodule4.service.impl.UserService;
import com.example.casestudymodule4.service.payment.MailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private VNPayService vnPayService;
    @Autowired
    MailService mailService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ISKProductService skuProductService;


    @GetMapping("")
    public String home(Model model) {
        return "index";
    }


    @PostMapping("/submitOrder")
    public String submidOrder(@ModelAttribute("payment") FormPayment formPayment,
                              @RequestParam("totalMoney") int orderTotal,
                              @RequestParam("userName") String userName,
                              @RequestParam("code") String orderInfo, @RequestParam("userId") Integer userId,
                              HttpSession session, Model model,
                              HttpServletRequest request) throws UnsupportedEncodingException {
        session.setAttribute("userName", userName);
        Order order = orderService.findOrderByCustomerAndStatus(userService.findById(userId), "processing");
        if (order == null) {
            cartService.save(formPayment);
            String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
            return "redirect:" + vnpayUrl;
        }
        model.addAttribute("showModal", "phong");
        return "index";
    }

    @GetMapping("/vnpay-payment")
    public ModelAndView GetMapping(HttpSession session, HttpServletRequest request) {
        int paymentStatus = vnPayService.orderReturn(request);
        String userName = (String) session.getAttribute("userName");
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        Double totalPrice = Double.valueOf(request.getParameter("vnp_Amount"));
        ModelAndView modelAndView = new ModelAndView();
        NumberFormat currencyFormatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        String formattedTotalPrice = currencyFormatter.format(totalPrice / 100);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH'h':mm'p'");
        LocalDateTime paymentDateTime = LocalDateTime.parse(paymentTime, inputFormatter);
        String formattedPaymentTime = paymentDateTime.format(outputFormatter);
        modelAndView.addObject("orderId", orderInfo);
        modelAndView.addObject("totalPrice", formattedTotalPrice);
        modelAndView.addObject("paymentTime", formattedPaymentTime);
        modelAndView.addObject("transactionId", transactionId);
        modelAndView.setViewName("payments/ordersuccess");
        if (paymentStatus == 1) {

            Order order = orderService.findByCode(orderInfo);
            if (order != null) {
                orderService.updateOrderStatus(order, "Payment success");
                Iterable<Cart> carts = cartService.findAll();
                for (Cart cart : carts) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setSku(cart.getSku());
                    orderDetail.setQuantity(cart.getQuantity());
                    orderService.saveOrder(orderDetail);
                    cartService.deleteCart();
                }

            }
        } else if (paymentStatus == 0) {
            Order order = orderService.findByCode(orderInfo);
            if (order != null) {
                orderService.updateOrderStatus(order, "Payment failed");
                orderService.deleteOrder(orderInfo);
            }
        }
        String emailStatus = mailService.sendMail(orderInfo, formattedPaymentTime, transactionId, formattedTotalPrice, userName);
        modelAndView.addObject("emailStatus", emailStatus);
        return modelAndView;
    }
}
