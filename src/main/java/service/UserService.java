package service;

import DAO.UserDAO;
import DAO.UserDaoFactory;
import model.User;

import java.util.List;

public class UserService {
    private static UserService userService;
    private UserDAO userDAO = UserDaoFactory.create();

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private UserService() {

    }

    public boolean addUser(User user) {
        User user1 = userDAO.getUserByName(user.getName());
        if (user1 == null) {
            userDAO.addUser(user);
            return true;
        }
        return false;
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    public boolean updateUser(User oldUser, User newUser) {
        if (!userDAO.checkingUser(newUser.getName(), newUser.getPassword())) {
            userDAO.updateUser(oldUser, newUser);
            return true;
        }
        return false;
    }

    public boolean deleteUser(Long id) {
        User user = userDAO.getUserById(id);
        if (user != null && userDAO.checkingUser(user.getName(), user.getPassword())) {
            userDAO.deleteUser(id);
            return true;
        }
        return false;
    }

    public void createTable() {
        userDAO.createTable();
    }
}
