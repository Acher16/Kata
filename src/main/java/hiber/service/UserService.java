package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> getListUsers();
    User getUserOwnsCar(String model, int series);
    void deleteAllUsers();
}
