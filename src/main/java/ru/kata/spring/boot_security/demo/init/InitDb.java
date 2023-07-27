package ru.kata.spring.boot_security.demo.init;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@Getter @Setter @RequiredArgsConstructor
public class InitDb {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        Role adminRole = new Role();
        adminRole.setId(1L);
        adminRole.setName("ROLE_ADMIN");

        Role userRole = new Role();
        userRole.setId(2L);
        userRole.setName("ROLE_USER");

        Set<Role> adminSet = new HashSet<>(Arrays.asList(adminRole, userRole));
        Set<Role> userSet = new HashSet<>(Arrays.asList(userRole));

        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User admin = new User();
        admin.setId(1L);
        admin.setName("admin");
        admin.setLastName("adminLast");
        admin.setAge(30);
        admin.setMail("admin@mail.ru");
        admin.setPassword(encoder.encode("admin"));
        admin.setRoles(adminSet);

        User user = new User();
        user.setId(2L);
        user.setName("user");
        user.setLastName("userLast");
        user.setAge(25);
        user.setMail("user@mail.ru");
        user.setPassword(encoder.encode("user"));
        user.setRoles(userSet);

        userRepository.saveAndFlush(admin);
        userRepository.saveAndFlush(user);
    }
}
