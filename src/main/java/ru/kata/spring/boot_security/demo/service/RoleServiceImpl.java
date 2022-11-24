package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository repository;
    @Autowired
    public void setRepository(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> getAll() {
        return repository.findAll();
    }

    @Override
    public void addRole(Role role) {

    }

    @Override
    public Role getRoleByName(String name) {
        return repository.getByName(name);
    }
}
