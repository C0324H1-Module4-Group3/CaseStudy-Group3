package com.example.casestudymodule4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "sku_products", schema = "module4_shop")
public class SkuProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Size(max = 100)
    @NotNull
    @Column(name = "size", nullable = false, length = 100)
    private String size;

    @Size(max = 100)
    @NotNull
    @Column(name = "color", nullable = false, length = 100)
    private String color;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @Size(max = 255)
    @NotNull
    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @Column(name = "quantity")
    private Integer quantity;

    @NotNull
    @ColumnDefault("'Còn'")
    @Lob
    @Column(name = "status", nullable = false)
    private String status;

}