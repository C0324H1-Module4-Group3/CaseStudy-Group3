package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.repository.UserRepo;
import com.example.casestudymodule4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepo userRepo;


    @Override
    public User findById(Integer id) {
        return userRepo.findById(id).orElse(null);
    }
}
