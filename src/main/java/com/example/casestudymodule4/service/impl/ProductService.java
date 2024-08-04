package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.SkuProduct;
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
    public List<SkuProduct> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(SkuProduct product) {
        productRepository.save(product);
    }

    @Override
    public Page<SkuProduct> findAll(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Page<SkuProduct> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public SkuProduct findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(SkuProduct product) {
        productRepository.delete(product);
    }

    @Override
    public void update(SkuProduct product) {
        productRepository.save(product);
    }
}
