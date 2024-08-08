package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.Category;
import com.example.casestudymodule4.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
