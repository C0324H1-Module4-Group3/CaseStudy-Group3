package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryId(Integer categoryId);

    List<Product> findByNameContaining(String keyword);

    Page<Product> findAll(Pageable pageable);
}
