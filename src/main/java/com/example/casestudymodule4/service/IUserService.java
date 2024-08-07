package com.example.casestudymodule4.service;

import com.example.casestudymodule4.dto.UserDto;
import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.model.VerificationToken;

public interface IUserService {
    User findById(Integer id);

    boolean save(UserDto userDto);

    VerificationToken getVerificationToken(String token);

    void verifyUser(User user);

    User getUserByUserName(String userName);

    void saveToken(VerificationToken userToken);

}
