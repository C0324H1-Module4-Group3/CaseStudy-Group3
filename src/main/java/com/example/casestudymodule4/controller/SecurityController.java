package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.dto.UserDto;
import com.example.casestudymodule4.service.IUserService;
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

@Controller
public class SecurityController {
    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String showPageLogin(){
//    public String showPageLogin(@CookieValue(value = "userName", defaultValue = "") String userName,
//                                @CookieValue(value = "password", defaultValue = "") String password,
//                                @RequestParam(value = "error", defaultValue = "") String error,
//                                Model model) {
//        model.addAttribute("userName", userName);
//        model.addAttribute("password", password);
//        model.addAttribute("error", error);
        return "security/login";
    }
    @GetMapping("/signup")
    public String signupPage(Model model, @RequestParam(value = "error", defaultValue = "false") boolean error) {
        model.addAttribute("error", error);
        model.addAttribute("userDto", new UserDto());
        return "security/signup";
    }
    @PostMapping("/login")
    public  String login(@RequestParam String username
                            , @RequestParam String password
                            , @RequestParam(value = "rememberMe", defaultValue = "false") boolean rememberMe
                            , HttpServletResponse response
                            , Model model){
        if (rememberMe){
            Cookie cookieUserName = new Cookie("username", username);
            cookieUserName.setHttpOnly(true);
            cookieUserName.setMaxAge(3600);
            response.addCookie(cookieUserName);
            Cookie cookiePassword = new Cookie("password", password);
            cookiePassword.setHttpOnly(true);
            cookiePassword.setMaxAge(3600);
            response.addCookie(cookiePassword);
        }
        return "redirect:/home";
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
        redirect.addFlashAttribute("message", "Thêm mới thành công");
        return "redirect:/login";
    }
}
