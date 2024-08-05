package com.example.casestudymodule4.service;

import com.example.casestudymodule4.dto.UserDto;
import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.model.VerificationToken;

public interface IUserService {
    boolean save(UserDto userDto);

    boolean saveUser(User user);

    boolean verifyUser(User user);

    VerificationToken getVerificationToken(String token);

    User getUserByUserName(String userName);

    void saveToken(VerificationToken userToken);
}
