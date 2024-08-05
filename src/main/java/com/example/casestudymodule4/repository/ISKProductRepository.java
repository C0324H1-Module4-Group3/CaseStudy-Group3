package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.SkuProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISKProductRepository extends JpaRepository<SkuProduct,Integer> {
    @Query("SELECT s FROM SkuProduct s JOIN s.product p WHERE p.name LIKE %:keyword%")
    List<SkuProduct> findByProductContaining(String keyword);
}
