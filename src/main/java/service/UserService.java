package service;

import DAO.UserHibernateDAO;
import model.User;

import java.util.List;

public class UserService {
    //private UserJdbcDAO userJdbcDAO = new UserJdbcDAO();
    UserHibernateDAO userHibernateDAO = new UserHibernateDAO();

    public UserService() {

    }

    public boolean addUser(User user) {
        User user1 = userHibernateDAO.getUserByName(user.getName());
        if (user1 == null) {
            userHibernateDAO.addUser(user);
            return true;
        }
        return false;
    }

    public List<User> getAllUsers() {
        return userHibernateDAO.getAllUsers();
    }

    public User getUserById(Long id) {
        return userHibernateDAO.getUserById(id);
    }

    public boolean updateUser(String oldName, String oldPassword, Long oldAge, String newName, String newPassword, Long newAge) {
        if (!userHibernateDAO.checkingUser(newName, newPassword) && userHibernateDAO.checkingUser(oldName, oldPassword)) {
            userHibernateDAO.updateUser(oldName, oldPassword, oldAge, newName, newPassword, newAge);
             return true;
        }
        return false;
    }

    public boolean deleteUser(Long id) {
        User user = userHibernateDAO.getUserById(id);
        if(user != null && userHibernateDAO.checkingUser(user.getName(),user.getPassword())) {
            userHibernateDAO.deleteUser(id);
             return true;
        }
        return false;
    }

    public void createTable() {
        userHibernateDAO.createTable();
    }
}
