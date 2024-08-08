package com.example.casestudymodule4.service;

import com.example.casestudymodule4.dto.ProductResponse;
import com.example.casestudymodule4.model.Product;
import com.example.casestudymodule4.model.SkuProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService extends IGenerateService<Product>{

    List<Product> findProductByCategoryId(Integer categoryId);

    Page<Product> findProductByCategory(Integer categoryId, Pageable pageable);
    Product findProductById(Integer productId);


    List<Product> searchProducts(String keyword);
    Page<Product> findAll(Pageable pageable);

    Page<ProductResponse> fetchProducts(Pageable pageable);

    Page<ProductResponse> searchProducts(String searchName, Pageable pageable);

    Page<ProductResponse> findProductByIdCategory(Integer categoryId, Pageable pageable);

}
