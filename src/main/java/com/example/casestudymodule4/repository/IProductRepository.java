package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Integer>{
}
