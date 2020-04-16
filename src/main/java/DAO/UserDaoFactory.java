package DAO;

import util.DBHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {
    public static UserDAO create() {
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = cl.getResourceAsStream("conn.properties");
            Properties properties = new Properties();
            assert inputStream != null;
            properties.load(inputStream);
            String typeOfDao = properties.getProperty("daotype");
            switch (typeOfDao) {
                case "hibernate": {
                    return new UserHibernateDAO();
                }
                case "jdbc":
                    return new UserJdbcDAO(DBHelper.getMySqlConnectionJDBC());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
