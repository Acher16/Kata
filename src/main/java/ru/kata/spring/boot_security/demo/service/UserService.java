package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    User getUserFromMail(String mail);
    List<User> getAllUsers();
    User getUserFromId(Long id);
    void saveUser(User user);
    void deleteUser(Long id);
    void updateUser(User user);
}
