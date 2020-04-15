package DAO;

import org.hibernate.SessionFactory;
import util.DBHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class UserDaoFactory {
    public static SessionFactory create() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("./../../../IdeaProjects/PreProect2/src/main/resources/conn.properties"));
            String typeOfDao = properties.getProperty("daotype");
            switch (typeOfDao) {
                case "hibernate": {
                    return DBHelper.getSessionFactory();
                }
                case "jdbc":
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
