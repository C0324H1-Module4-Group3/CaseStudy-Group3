package com.example.casestudymodule4.dto;

import com.example.casestudymodule4.annotation.UniqueEmail;
import jakarta.validation.constraints.*;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class UserDto implements Validator {
    private Integer id;

    @NotBlank(message = "Name could not be blank")
    @Pattern(regexp = "^[A-Za-z, ]{3,100}$", message = "Input is invalid")
    private String name;

    @NotNull
    private LocalDate dob;

    @NotBlank(message = "Name could not be blank")
    private String address;

    @NotBlank(message = "Phone could not be blank")
    @Pattern(regexp = "^[0-9]{10}$", message = "Input is invalid")
    private String phoneNumber;

    @UniqueEmail
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$",message = "email is not valid"
    )
    private String userName;
    @Pattern(regexp = "^[A-Za-z]+[0-9]+$", message = "Input is invalid")
    @Size(min = 6, message = "Password contains at least 6 character")
    private String password;

    private String confirmPassword;

    public UserDto() {
    }

    public UserDto(Integer id, String name, LocalDate dob, String address, String phoneNumber, String userName, String password, String confirmPassword) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public UserDto(String name, LocalDate dob, String address, String phoneNumber, String userName, String password, String confirmPassword) {
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto user = (UserDto) target;
        if(!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword","","Confirm Password does not match");
        }
        if(user.address.length() < 10) {
            errors.rejectValue("address", "", "Address must be at least 10 characters");
        } else if (user.address.length() >255) {
            errors.rejectValue("address", "", "Address is too long");
        }
        if(!user.getDob().isBefore(LocalDate.now()) | (LocalDate.of(1920,1,1)).isAfter(user.getDob())) {
            errors.rejectValue("dob","", "Date of Birth is invalid");
        }
    }
}
