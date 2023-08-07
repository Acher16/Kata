package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class RestUserController {
    private final UserService userService;

    @GetMapping
    public User getUser(Principal principal) {
        return userService.getUserFromMail(principal.getName());
    }
}
