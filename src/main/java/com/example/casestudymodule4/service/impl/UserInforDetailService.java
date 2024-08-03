package com.example.casestudymodule4.service.impl;


import com.example.casestudymodule4.dto.UserInfoUserDetails;
import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.model.UserRole;
import com.example.casestudymodule4.repository.IUserRepository;
import com.example.casestudymodule4.repository.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInforDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IUserRoleRepository iUserRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserRepository.findByUserName(username);
        //        Lấy tất cả role của AppUser
        List<UserRole> userRoles = iUserRoleRepository.findAllByUser(user);
        UserInfoUserDetails infoUserDetails = new UserInfoUserDetails(user, userRoles);
        return infoUserDetails;
    }
}
