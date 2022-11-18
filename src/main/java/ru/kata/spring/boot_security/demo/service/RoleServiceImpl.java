package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository repository;

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> getAll() {
        return repository.findAll();
    }

    @Override
    public void addRole(Role role) {
        repository.save(role);
    }

    @Override
    public Role findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Set<Role> findByIdRoles(List<Long> roles) {
        return null;
    }

    @Override
    public void addDefaultRole() {
        addRole(new Role(1L, "ROLE_USER"));
        addRole(new Role(2L, "ROLE_ADMIN"));
    }
}
