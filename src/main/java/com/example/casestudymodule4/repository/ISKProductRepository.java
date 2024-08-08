package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.SkuProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISKProductRepository extends JpaRepository<SkuProduct,Integer> {

    SkuProduct findSkuProductByIdEqualsAndSizeEquals(Integer id,String size);
    SkuProduct findSkuProductByProductIdAndSize(Integer productId,String size);

    @Query("SELECT s FROM SkuProduct s JOIN s.product p WHERE p.name LIKE %:keyword%")
    Page<SkuProduct> findByProductContaining(String keyword,Pageable pageable);
    Page<SkuProduct> findByProductCategoryId(Integer categoryId, Pageable pageable);
    Page<SkuProduct> findByProductNameContainingIgnoreCase(String searchName, Pageable pageable);


}
