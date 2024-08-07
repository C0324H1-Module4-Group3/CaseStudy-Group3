package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.SkuProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISKProductRepository extends JpaRepository<SkuProduct,Integer> {
    SkuProduct findSkuProductByIdEqualsAndSizeEquals(Integer id,String size);
    SkuProduct findSkuProductByProductIdAndSize(Integer productId,String size);
}
