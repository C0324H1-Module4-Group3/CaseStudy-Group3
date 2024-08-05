package com.example.casestudymodule4.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGenerateService<T> {
    List<T> findAll();

    void save(T t);

    Page<T> findAll(String name, Pageable pageable);

    T findById(Integer id);

    void delete(T t);

    void update(T t);
}
