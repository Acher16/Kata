package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    private static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 20);
        userService.saveUser("Petr", "Petrov", (byte) 30);
        userService.saveUser("Ivan", "Petrov", (byte) 23);
        userService.saveUser("Petr", "Ivanov", (byte) 31);

        List<User> list = userService.getAllUsers();
        list.forEach(System.out::println);

        userService.removeUserById(2);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
