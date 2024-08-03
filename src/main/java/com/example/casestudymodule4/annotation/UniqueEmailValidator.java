package com.example.casestudymodule4.annotation;

import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.repository.IUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        for (User user : userRepository.findAll()){
            if (user.getUserName().equals(name)){
                return false;
            }
        }
        return true;
    }
}