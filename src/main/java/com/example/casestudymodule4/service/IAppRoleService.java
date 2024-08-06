package com.example.casestudymodule4.service;

import com.example.casestudymodule4.model.AppRole;

public interface IAppRoleService {
    AppRole findByRoleName(String roleUser);
}
