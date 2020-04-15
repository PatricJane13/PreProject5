package DAO;

import model.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    public UserJdbcDAO() {
    }

    public User getUserByName(String name) {
        try (PreparedStatement preparedStatement = DBHelper.getMySqlConnectionJDBC().prepareStatement("SELECT * FROM register_table WHERE NAME=?")) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("password"), resultSet.getLong("age"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(Long id) {
        try (PreparedStatement preparedStatement = DBHelper.getMySqlConnectionJDBC().prepareStatement("SELECT * FROM register_table WHERE id=?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("password"), resultSet.getLong("age"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(User user) {
        try (PreparedStatement preparedStatement = DBHelper.getMySqlConnectionJDBC().prepareStatement("INSERT INTO register_table (NAME, password, age) VALUES (?,?,?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getAge());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Statement statement = DBHelper.getMySqlConnectionJDBC().createStatement()) {
            List<User> usersList = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM register_table");
            while (resultSet.next()) {
                usersList.add(new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("password"), resultSet.getLong("age")));
            }
            return usersList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(User oldUser, User newUser) {
        try (PreparedStatement preparedStatement = DBHelper.getMySqlConnectionJDBC().prepareStatement("UPDATE register_table SET NAME = ?, password =?, age=? WHERE NAME =? AND password =? AND age =?")) {
            preparedStatement.setString(1, newUser.getName());
            preparedStatement.setString(2, newUser.getPassword());
            preparedStatement.setLong(3, newUser.getAge());
            preparedStatement.setString(4, oldUser.getName());
            preparedStatement.setString(5, oldUser.getPassword());
            preparedStatement.setLong(6, oldUser.getAge());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(Long id) {
        try (PreparedStatement preparedStatement = DBHelper.getMySqlConnectionJDBC().prepareStatement("DELETE FROM register_table WHERE NAME =? AND password=?")) {
            User user = getUserById(id);
            if (user != null) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkingUser(String name, String password) {
        try (PreparedStatement preparedStatement = DBHelper.getMySqlConnectionJDBC().prepareStatement("SELECT * FROM register_table WHERE NAME =? AND password=?")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createTable() {
        try (Statement statement = DBHelper.getMySqlConnectionJDBC().createStatement()) {
            statement.execute("create table if not exists register_table\n" +
                    "(\n" +
                    "    id       bigint       not null auto_increment,\n" +
                    "    name     varchar(256),\n" +
                    "    password varchar(256),\n" +
                    "    age      bigint (3),\n" +
                    "    primary key (id)\n" +
                    ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
