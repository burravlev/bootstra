package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User>getAllUsers ();
    User getUserById(Integer id);
    void save(User user);
    void delete(Integer id);
    void update(User user);
    User findByUsername(String username);
    void defaultUsers();
}
