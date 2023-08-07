package ru.kata.spring.boot_security.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class RestAdminController {
    private final UserService userService;

    @GetMapping("users")
    public List<User> getUsersAll() {
        return userService.getAllUsers();
    }

    @GetMapping("users/{id}")
    public User getUserFromId (@PathVariable Long id) {
        return userService.getUserFromId(id);
    }

    @PostMapping("users")
    public User createUser(@RequestBody User user) {
        System.out.println("---------------------------------------------------------\n");
        System.out.println(user);
        System.out.println("---------------------------------------------------------\n");
        userService.saveUser(user);
        return user;
    }

    @PutMapping("users")
    public User updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User with id = " + id +" delete";
    }
}
