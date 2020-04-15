package DAO;

import org.hibernate.SessionFactory;
import util.DBHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
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
                    return new UserJdbcDAO();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
