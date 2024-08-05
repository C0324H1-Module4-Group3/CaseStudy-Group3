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
        return userRepository.findById(id).orElse(null);
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
        user.setDob(userDto.getDob());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnable(false);
        userRepository.save(user);
        return true;

    }
    public boolean saveUser(User user){
        List<User> userList = userRepository.findAll();
        for(User appUser : userList) {
            if(appUser.getUserName().equals(user.getUserName())) {
                return false;
            }
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean verifyUser(User user) {
        userRepository.save(user);
        return true;
    }


    @Override
    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findByToken(token);
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
