package ru.kata.spring.boot_security.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public User getUserFromMail(String mail) {
        return userRepository.findByMail(mail);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserFromId(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void saveUser(User user) {
        Set<Role> usersRoles = new HashSet<>();
        for (Role role : roleRepository.findAll()) {
            for (Role userRole : user.getRoles()) {
                if (role.getName().equals(userRole.getName())) usersRoles.add(role);
            }
        }
        user.setRoles(usersRoles);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }
}
