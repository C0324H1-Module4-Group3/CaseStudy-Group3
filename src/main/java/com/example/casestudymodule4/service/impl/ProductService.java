package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.dto.ProductResponse;
import com.example.casestudymodule4.dto.SkuProductResponse;
import com.example.casestudymodule4.model.Product;


import com.example.casestudymodule4.model.SkuProduct;
import com.example.casestudymodule4.repository.IProductRepository;
import com.example.casestudymodule4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Page<Product> findProductByCategory(Integer categoryId, Pageable pageable) {
        return productRepository.findByCategory(categoryId, pageable);
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
    public Page<ProductResponse> fetchProducts(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        List<ProductResponse> productResponses = new ArrayList<>();

        for(Product product : productPage){
            List<SkuProductResponse> skuProducts = product.getSkuProducts().stream().map(skuProduct ->
                    SkuProductResponse.builder()
                            .id(skuProduct.getId())
                            .productId(skuProduct.getProduct().getId())
                            .color(skuProduct.getColor())
                            .size(skuProduct.getSize())
                            .price(skuProduct.getPrice())
                            .build()).toList();
            SkuProductResponse firstSkuProduct = skuProducts.isEmpty() ? null : skuProducts.get(0);

            productResponses.add(ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .imagePath(product.getImagePath())
                    .price(firstSkuProduct.getPrice())
                    .categoryId(product.getCategory().getId())
                    .build());
        }
        return new PageImpl<>(productResponses, pageable, productPage.getTotalElements());
    }

    @Override
    public Page<ProductResponse> searchProducts(String searchName, Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        List<ProductResponse> productResponses = new ArrayList<>();

        for(Product product : productPage){
            List<SkuProductResponse> skuProducts = product.getSkuProducts().stream().map(skuProduct ->
                    SkuProductResponse.builder()
                            .id(skuProduct.getId())
                            .productId(skuProduct.getProduct().getId())
                            .color(skuProduct.getColor())
                            .size(skuProduct.getSize())
                            .price(skuProduct.getPrice())
                            .build()).toList();
            SkuProductResponse firstSkuProduct = skuProducts.isEmpty() ? null : skuProducts.get(0);

            productResponses.add(ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .imagePath(product.getImagePath())
                    .price(firstSkuProduct.getPrice())
                    .categoryId(product.getCategory().getId())
                    .build());
        }
        return new PageImpl<>(productResponses, pageable, productPage.getTotalElements());
    }

    @Override
    public Page<ProductResponse> findProductByIdCategory(Integer categoryId, Pageable pageable) {
        Page<Product> productPage = productRepository.findByIdCategory(categoryId, pageable);
        List<ProductResponse> productResponses = new ArrayList<>();

        for(Product product : productPage){
            List<SkuProductResponse> skuProducts = product.getSkuProducts().stream().map(skuProduct ->
                    SkuProductResponse.builder()
                            .id(skuProduct.getId())
                            .productId(skuProduct.getProduct().getId())
                            .color(skuProduct.getColor())
                            .size(skuProduct.getSize())
                            .price(skuProduct.getPrice())
                            .build()).toList();
            SkuProductResponse firstSkuProduct = skuProducts.isEmpty() ? null : skuProducts.get(0);

            productResponses.add(ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .imagePath(product.getImagePath())
                            .price(firstSkuProduct.getPrice())
                    .categoryId(product.getCategory().getId())
                    .build());
        }
        return new PageImpl<>(productResponses, pageable, productPage.getTotalElements());
    }
}
