package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.SkuProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<SkuProduct,Long>{
}
