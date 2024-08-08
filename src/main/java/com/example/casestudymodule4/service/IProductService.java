package com.example.casestudymodule4.service;

import com.example.casestudymodule4.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService extends IGenerateService<Product>{

    List<Product> findProductByCategoryId(Integer categoryId);

    Page<Product> findProductByCategory(Integer categoryId, Pageable pageable);
    Product findProductById(Integer productId);


    List<Product> searchProducts(String keyword);

    Page<Product> fetchProducts(Pageable pageable);

    Page<Product> searchProducts(String searchName, Pageable pageable);
}
