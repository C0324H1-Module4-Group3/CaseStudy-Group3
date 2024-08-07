package com.example.casestudymodule4.service;

import com.example.casestudymodule4.model.SkuProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISKProductService extends IGenerateService<SkuProduct>{
    Page<SkuProduct> findAll(Pageable pageable);

    void remove(Integer id);

    List<SkuProduct> searchSkuProducts(String keyword);
}
