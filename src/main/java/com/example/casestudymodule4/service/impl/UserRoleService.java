package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.AppRole;
import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.model.UserRole;
import com.example.casestudymodule4.repository.IUserRoleRepository;
import com.example.casestudymodule4.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService implements IUserRoleService {
    @Autowired
    private IUserRoleRepository userRoleRepository;
    @Override
    public void save(User user, AppRole roleUser) {
        userRoleRepository.save(new UserRole(user, roleUser));
    }
}
