package DAO;

import model.User;

import java.util.List;

public interface UserDAO {
    User getUserByName(String name);

    User getUserById(Long id);

    void addUser(User user);

    List<User> getAllUsers();

    void updateUser(String oldName, String oldPassword, Long oldAge, String newName, String newPassword, Long newAge);

    void deleteUser(Long id);

    boolean checkingUser(String name, String password);

    void createTable();
}
