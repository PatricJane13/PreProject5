package service;

import DAO.UserJdbcDAO;
import model.User;

import java.util.List;

public class UserService {
    private UserJdbcDAO userJdbcDAO = new UserJdbcDAO();

    public UserService() {

    }

    public boolean addUser(User user) {
        User user1 = userJdbcDAO.getUserByName(user.getName());
        if (user1 == null) {
            userJdbcDAO.addUser(user);
            return true;
        }
        return false;
    }

    public List<User> getAllUsers() {
        return userJdbcDAO.getAllUsers();
    }

    public User getUserById(Long id) {
        return userJdbcDAO.getUserById(id);
    }

    public boolean updateUser(String oldName, String oldPassword, Long oldAge, String newName, String newPassword, Long newAge) {
        if (!userJdbcDAO.checkingUser(newName, newPassword) && userJdbcDAO.checkingUser(oldName, oldPassword)) {
             userJdbcDAO.updateUser(oldName, oldPassword, oldAge, newName, newPassword, newAge);
             return true;
        }
        return false;
    }

    public boolean deleteUser(Long id) {
        User user = userJdbcDAO.getUserById(id);
        if(user != null && userJdbcDAO.checkingUser(user.getName(),user.getPassword())) {
             userJdbcDAO.deleteUser(id);
             return true;
        }
        return false;
    }

    public void createTable() {
         userJdbcDAO.createTable();
    }
}
