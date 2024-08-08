package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.dto.CartDto;
import com.example.casestudymodule4.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>, CrudRepository<Cart, Integer> {
    @Query("SELECT c FROM Cart c " +
            "join fetch c.sku sp " +
            "join fetch sp.product p " +
            "join fetch c.customer customer " +
            "where customer.id = :userId")
    Iterable<Cart> findCartsByUserId(Integer userId);

    @Query("SELECT c.quantity as quantity, p.name as name, s.price as price  FROM Cart c " +
            " join  c.sku s " +
            " join  s.product p " +
            "join  c.customer customer " +
            "where  customer.id = :customerId")
    Iterable<CartDto> findAllByCustomerId(Integer customerId);
    Iterable<Cart> findByCustomerId(Integer userId);

}


