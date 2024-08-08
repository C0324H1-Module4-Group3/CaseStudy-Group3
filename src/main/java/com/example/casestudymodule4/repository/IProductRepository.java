package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryId(Integer categoryId);

    @Query("select p from Product p INNER JOIN Category c ON c.id = p.category.id " +
            "WHERE c.id = :categoryId")
    Page<Product> findByCategory(@Param("categoryId") Integer categoryId, Pageable pageable);

    List<Product> findByNameContaining(String keyword);

    Page<Product> findAll(Pageable pageable);

    @Query("select p from Product p where p.name like %:nameSearch%")
    Page<Product> searchProduct(@Param("nameSearch") String nameSearch, Pageable pageable);

    @Query("select p from Product p " +
            "inner join SkuProduct sku on sku.product.id = p.id " +
            "and sku.id = (select MIN(sp.id) from SkuProduct sp where sp.product.id = p.id)")
    Page<Product> fetchProducts(Pageable pageable);

    @Query("select p from Product p " +
            "inner join SkuProduct sku on sku.product.id = p.id " +
            "where p.name like %:nameSearch% " +
            "and sku.id = (select MIN(sp.id) from SkuProduct sp where sp.product.id = p.id)")
    Page<Product> findProductByName(@Param("nameSearch") String nameSearch, Pageable pageable);

    @Query("select p from Product p " +
            "inner join SkuProduct sku on sku.product.id = p.id " +
            "inner join Category c on c.id = p.category.id " +
            "where c.id = :categoryId " +
            "and sku.id = (select MIN(sp.id) from SkuProduct sp where sp.product.id = p.id)")
    Page<Product> findByIdCategory(@Param("categoryId") Integer categoryId, Pageable pageable);
}
