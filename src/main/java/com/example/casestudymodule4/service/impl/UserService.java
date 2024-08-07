package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.dto.UserDto;
import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.model.VerificationToken;
import com.example.casestudymodule4.repository.IUserRepository;
import com.example.casestudymodule4.repository.IVerificationTokenRepository;
import com.example.casestudymodule4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IVerificationTokenRepository verificationTokenRepository;
    @Override
    public User findById(Integer id) {
        return null;
    }
    @Override
    public boolean save(UserDto userDto) {
        List<User> userList = userRepository.findAll();
        for(User appUser : userList) {
            if(appUser.getUserName().equals(userDto.getUserName())) {
                return false;
            }
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setAddress(userDto.getAddress());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setUserName(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnable(false);
//        user.setRole();
        userRepository.save(user);
        return true;
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public void verifyUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void saveToken(VerificationToken userToken) {
        verificationTokenRepository.save(userToken);
    }
}
