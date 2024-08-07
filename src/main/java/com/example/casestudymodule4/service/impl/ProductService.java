package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.Product;



import com.example.casestudymodule4.repository.IProductRepository;
import com.example.casestudymodule4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public Page<Product> findAll(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Product findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public List<Product> findProductByCategoryId(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public Product findProductById(Integer productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

    @Override
    public Page<Product> fetchProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
