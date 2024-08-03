package com.example.casestudymodule4.service;

import com.example.casestudymodule4.model.Product;

import java.util.List;

public interface IProductService extends IGenerateService<Product>{
    List<Product> findProductByCategoryId(Integer categoryId);
}
