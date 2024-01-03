package com.example.site.controller;

import com.example.site.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/main")
    public String main() {
        return "pages/home";
    }
    @GetMapping("/home")
    public String home() {
        return "pages/home";
    }

}
