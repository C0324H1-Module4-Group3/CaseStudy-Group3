package com.example.casestudymodule4.service;


import com.example.casestudymodule4.dto.UserDto;
import com.example.casestudymodule4.model.User;

public interface IUserService {
    boolean save(UserDto userDto);
    User findById(Integer id);

}
