package com.example.casestudymodule4.service;

import com.example.casestudymodule4.model.AppRole;
import com.example.casestudymodule4.model.User;

public interface IUserRoleService {
    void save(User user, AppRole roleUser);
}
