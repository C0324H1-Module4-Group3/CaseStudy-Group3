package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.SkuProduct;
import com.example.casestudymodule4.repository.ISKProductRepository;
import com.example.casestudymodule4.service.ISKProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SKProductService implements ISKProductService {
    @Autowired
    private ISKProductRepository iskProductRepository;

    @Override
    public List<SkuProduct> findAll() {
        return iskProductRepository.findAll();
    }

    @Override
    public void save(SkuProduct product) {
        iskProductRepository.save(product);
    }

    @Override
    public Page<SkuProduct> findAll(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Page<SkuProduct> findAll(Pageable pageable) {
        return iskProductRepository.findAll(pageable);
    }

    @Override
    public void remove(Integer id) {
        iskProductRepository.deleteById(id);
    }

    @Override

    public SkuProduct findSkuProductByIdEqualsAndSizeEquals(Integer id, String size) {
        return iskProductRepository.findSkuProductByIdEqualsAndSizeEquals(id, size);
    }

    @Override
    public Page<SkuProduct> searchSkuProducts(String keyword, Pageable pageable) {
        return iskProductRepository.findByProductContaining(keyword,pageable);
    }

    @Override
    public SkuProduct findById(Integer id) {
        return iskProductRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(SkuProduct product) {
        iskProductRepository.delete(product);
    }

    @Override
    public void update(SkuProduct product) {
        iskProductRepository.save(product);
    }
    @Override
    public Page<SkuProduct> fetchSkuProducts(Pageable pageable) {
        return iskProductRepository.findAll(pageable);
    }

    @Override
    public Page<SkuProduct> findSkuProductByCategory(Integer categoryId, Pageable pageable) {
        return iskProductRepository.findByProductCategoryId(categoryId, pageable);
    }

    @Override
    public Page<SkuProduct> searchSkuProducts(String searchName, Pageable pageable) {
        // Assuming search by product name. Modify if needed.
        return iskProductRepository.findByProductNameContainingIgnoreCase(searchName, pageable);
    }
}

