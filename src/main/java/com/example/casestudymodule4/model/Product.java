package com.example.casestudymodule4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "products", schema = "module4_shop")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Size(max = 255)
    @NotNull
    @Column(name = "image_path", nullable = false)
    private String imagePath;
<<<<<<< HEAD
=======

    @OneToMany(mappedBy = "product")
    private List<SkuProduct> skuProducts;

>>>>>>> 338a248ed4f3739fe22a16f24d8b615c67b875b6
}