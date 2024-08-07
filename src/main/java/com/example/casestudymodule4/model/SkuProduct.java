package com.example.casestudymodule4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "sku_products", schema = "module4_shop")
public class SkuProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "Product cannot be null")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull(message = "Size cannot be null")
    @Pattern(regexp = "^(M|L|X|XL)$", message = "Size must be one of the following: M, L, X, XL")
    @Column(name = "size", nullable = false, length = 100)
    private String size;

    @Size(max = 100)
    @NotNull(message = "Color cannot be null")
    @Column(name = "color", nullable = false, length = 100)
    private String color;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price cannot be negative")
    @Column(name = "price", nullable = false)
    private Integer price;

    @Size(max = 255)
    @NotNull(message = "Image path cannot be null")
    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @Min(value = 0, message = "Quantity cannot be negative")
    @Column(name = "quantity")
    private Integer quantity;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany
    @JoinColumn(name = "sku_id" )
    private Set<Cart> carts;
}