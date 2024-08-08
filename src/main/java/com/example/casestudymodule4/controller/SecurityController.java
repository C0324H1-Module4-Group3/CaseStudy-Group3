package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.dto.UserDto;
import com.example.casestudymodule4.model.User;
import com.example.casestudymodule4.model.VerificationToken;
import com.example.casestudymodule4.service.IUserService;
import com.example.casestudymodule4.service.impl.EmailService;
import com.example.casestudymodule4.service.impl.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class SecurityController {
    @Autowired
    private IUserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/login")
    public String showPageLogin(Principal principal, Model model){
        if (principal != null){
            return "redirect:/home";
        }
        model.addAttribute("message", "Account is invalid");
        return "security/login";
    }
    @GetMapping("/signup")
    public String signupPage(Model model, @RequestParam(value = "error", defaultValue = "false") boolean error) {
        model.addAttribute("error", error);
        model.addAttribute("userDto", new UserDto());
        return "security/signup";
    }

    @PostMapping(value="/signup")
    public String createUser(@Validated @ModelAttribute UserDto userDto, BindingResult bindingResult, Model model, RedirectAttributes redirect) {
        new UserDto().validate(userDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "security/signup";
        }
        boolean isAdded = userService.save(userDto);
        if(!isAdded){
            model.addAttribute("error","true");
            return "security/signup";
        }
        User newUser = userService.getUserByUserName(userDto.getUserName());
        UUID uuid = UUID.randomUUID();
        String tokenString = uuid.toString();
        VerificationToken userToken = new VerificationToken(tokenString, newUser, LocalDateTime.now().plusHours(8));
        userService.saveToken(userToken);
        emailService.sendVerificationEmail(userToken);
        redirect.addFlashAttribute("message", "Account created successfully.Please verify your email");
        return "redirect:/login";
    }

    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "security/logoutSuccessfulPage";
    }
}
