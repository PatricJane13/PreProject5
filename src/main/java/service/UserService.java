package service;

import DAO.UserJdbcDAO;
import model.User;
import java.util.List;

public class UserService {
    private UserJdbcDAO userJdbcDAO = UserJdbcDAO.getInstance();

    public UserService(){

    }

    public boolean addUser(User user) {
        User user1 = userJdbcDAO.getUserByName(user.getName());
        if (user1 == null) {
            userJdbcDAO.addUser(user);
            return true;
        }
        return false;
    }

    public List<User> getAllUsers(){
        return userJdbcDAO.getAllUsers();
    }

    public User getUserById(Long id) {
        return userJdbcDAO.getUserById(id);
    }

    public boolean updateUser(String oldName, String oldPassword, Long oldAge, String newName, String newPassword,Long newAge){
        if (!userJdbcDAO.checkingUser(newName,newPassword)) {
            return userJdbcDAO.updateUser(oldName, oldPassword, oldAge, newName, newPassword, newAge);
        }
        return false;
    }

    public boolean deleteUser(Long id){
        return userJdbcDAO.deleteUser(id);
    }

    public boolean createTable() {
        return userJdbcDAO.createTable();
    }
    //Ghbndtn
}
