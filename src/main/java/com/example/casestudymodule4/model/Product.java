package com.example.casestudymodule4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Size(max = 255)
    @NotNull
    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @OneToMany(mappedBy = "product")
    private Set<SkuProduct> skuProducts = new HashSet<>();

}