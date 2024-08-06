package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String roleUser);
}
