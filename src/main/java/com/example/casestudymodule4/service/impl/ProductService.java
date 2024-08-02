package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.Product;
import com.example.casestudymodule4.service.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ProductService implements IProductService {
    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public Page<Product> findAll(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public void update(Product product) {

    }
}
