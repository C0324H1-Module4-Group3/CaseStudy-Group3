package com.example.casestudymodule4.service;

import com.example.casestudymodule4.model.SkuProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IGenerateService<SkuProduct>{

    Page<SkuProduct> findAll(Pageable pageable);
}
