package com.example.site.controller;

import com.example.site.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.site.services.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    @GetMapping("/profile")
    public String showUserProfile( User user, Model model) {
        model.addAttribute("user", user);
        return "users/profile";
    }
    @GetMapping("/main")
    public String mainPage(Model model) {
        return "pages/home";
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", usersService.getAllUsers());
        return "users/list";
    }

    @GetMapping("/{userId}")
    public String getUserById(@PathVariable int userId, Model model) {
        model.addAttribute("user", usersService.getUserById(userId));
        return "users/details";
    }

    @GetMapping("/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute User user) {
        usersService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{userId}/edit")
    public String showEditForm(@PathVariable int userId, Model model) {
        model.addAttribute("user", usersService.getUserById(userId));
        return "users/edit";
    }

    @PostMapping("/{userId}/edit")
    public String editUser(@PathVariable int userId, @ModelAttribute User user) {
        user.setUserId(userId);
        usersService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{userId}/delete")
    public String deleteUser(@PathVariable int userId) {
        usersService.deleteUserById(userId);
        return "redirect:/users";
    }
}
