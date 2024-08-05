package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
}
