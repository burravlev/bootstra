package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String adminPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAll());
        return "admin/admin";
    }

    @GetMapping("/new")
    public String newUser(@AuthenticationPrincipal User user, Model model) {

        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAll());
        return "admin/new";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute User user,
                             @RequestParam("userRoles") String[] roles) {
        for (String role : roles) {
            user.addRole(roleService.getRoleByName(role));
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @PutMapping("/update/{id}")
    public String updateUser(
            @ModelAttribute("user") User user,
            @RequestParam("userRoles") String[] roles,
            @PathVariable("id") Integer id) {

        for (String role : roles) {
            user.addRole(roleService.getRoleByName(role));
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
