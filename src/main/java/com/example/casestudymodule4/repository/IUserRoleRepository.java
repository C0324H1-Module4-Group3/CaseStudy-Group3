package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findAllByUser(User user);
}
