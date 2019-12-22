package com.jawadaz.springsecurity01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "myLogin";
    }

    @GetMapping("/login-error")
    public String showLoginError(Model model) {
        model.addAttribute("loginError", true);
        return "myLogin";
    }

}
