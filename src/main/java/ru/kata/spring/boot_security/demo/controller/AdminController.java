package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private final UserService userService;

    @Autowired()
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        System.out.println(userService.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/save")
    public String saveUser(User user, Model model) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("title", "Update " + user.getName());
        model.addAttribute("user", user);
        return "update";
    }

    @PatchMapping("/update")
    public String update(User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping ("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}