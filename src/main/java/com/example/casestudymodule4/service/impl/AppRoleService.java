package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.AppRole;
import com.example.casestudymodule4.repository.IAppRoleRepository;
import com.example.casestudymodule4.service.IAppRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppRoleService implements IAppRoleService {
    @Autowired
    private IAppRoleRepository appRoleRepository;

    @Override
    public AppRole findByRoleName(String roleUser) {
        return appRoleRepository.findByRoleName(roleUser);
    }
}
