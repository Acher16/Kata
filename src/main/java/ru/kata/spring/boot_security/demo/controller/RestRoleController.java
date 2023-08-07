package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RestRoleController {
    private final RoleRepository roleRepository;

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
