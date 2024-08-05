package com.example.casestudymodule4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "orders", schema = "module4_shop")
public class    Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Size(max = 100)
    @Column(name = "payment_method", length = 100)
    private String paymentMethod;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Size(max = 255)
    @Column(name = "delivery_address")
    private String deliveryAddress;
}
