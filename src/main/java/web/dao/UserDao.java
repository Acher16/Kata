package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void save(User user);
    User getUserFromId(int id);
    void updateUser(int id, User user);
    void deleteUser(int id);
}