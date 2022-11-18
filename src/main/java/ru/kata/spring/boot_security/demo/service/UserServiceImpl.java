package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private RoleService roleService;
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    public UserServiceImpl(RoleService roleService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(getUserById(id));
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(
                    "User " + username + " not found!"
            );
        }

        return user;
    }

    @Override
    public void defaultUsers() {
        roleService.addDefaultRole();
        List<Role> roleUser = new ArrayList<>();
        roleUser.add(roleService.findById(1L));
        List<Role> roleAdmin = new ArrayList<>();
        roleAdmin.add(roleService.findById(1L));
        roleAdmin.add(roleService.findById(2L));
        User user1 = new User("Ivan", "Antonov", (byte) 22, "user", "$2a$12$VWXn.UpIufqfOA6xCYSzDubBfSTCIAtgFY.kKgzFHlmzAlT8FeO9q", roleUser);
        User user2 = new User("Anton", "Ivanov", (byte) 34, "admin", "$2a$12$YRHTl5Ie97i/fJENrVZS2es0LuIrd8HZgZQ19RO/4U8NTF9P/9BEi", roleAdmin);
        save(user1);
        save(user2);
    }
}