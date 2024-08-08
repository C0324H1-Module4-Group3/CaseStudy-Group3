package com.example.casestudymodule4.service;

import com.example.casestudymodule4.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();

    Category findById(Long id);
}
