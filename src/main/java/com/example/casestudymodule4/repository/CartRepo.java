package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>, CrudRepository<Cart, Integer> {
    @Query("SELECT c FROM Cart c " +
            "JOIN FETCH c.sku sp " +
            "JOIN FETCH sp.product p " +
            "JOIN FETCH c.customer u " +
            "WHERE u.id = :userId")
    List<Cart> findCartsByUserId(Integer userId);


}
