package com.example.spring_boot.service;

import com.example.spring_boot.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user);

    User getUserFromId(Long id);

    void updateUser(User user);

    void deleteUser(Long id);
}
