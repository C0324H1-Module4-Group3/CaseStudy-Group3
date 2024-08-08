package com.example.casestudymodule4.service;

import com.example.casestudymodule4.model.SkuProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISKProductService extends IGenerateService<SkuProduct>{
    Page<SkuProduct> findAll(Pageable pageable);
    void remove(Integer id);
    SkuProduct findSkuProductByIdEqualsAndSizeEquals(Integer id,String size);
    Page<SkuProduct> fetchSkuProducts(Pageable pageable);
    Page<SkuProduct> findSkuProductByCategory(Integer categoryId, Pageable pageable);
    Page<SkuProduct> searchSkuProducts(String searchName, Pageable pageable);
    Page<SkuProduct> searchSkuProduct(String searchName, Pageable pageable);
}
