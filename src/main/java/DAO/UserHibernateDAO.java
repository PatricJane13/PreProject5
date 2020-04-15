package DAO;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private SessionFactory sessionFactory = DBHelper.dbHelper().getSessionFactory();

    @Override
    public User getUserByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE name = :name");
        query.setParameter("name", name);
        User user = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public User getUserById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT id FROM User WHERE id = :id");
        query.setParameter("id", id);
        List<Long> list = query.list();
        User user = (User) session.get(User.class, list.get(0));
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> list = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public void updateUser(String oldName, String oldPassword, Long oldAge, String newName, String newPassword, Long newAge) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET name= :newName, password = :newPassword, age = :newAge WHERE name = :oldName AND password = :oldPassword AND age = :oldAge");
        query.setParameter("newName", newName);
        query.setParameter("newPassword", newPassword);
        query.setParameter("newAge", newAge);
        query.setParameter("oldName", oldName);
        query.setParameter("oldPassword", oldPassword);
        query.setParameter("oldAge", oldAge);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUser(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = getUserById(id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public boolean checkingUser(String name, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE name =:name AND password =:password");
        query.setParameter("name", name);
        query.setParameter("password", password);
        User user = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return user != null;
    }

    @Override
    public void createTable() {

    }
}
