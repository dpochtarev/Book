package DAO.Impl;


import DAO.UserDAO;
import main.resources.User;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;

public class UserDAOImpl implements UserDAO{

    public void addUser(User user) throws SQLException{
        Session session = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();


        }catch (Exception e){
            e.printStackTrace();
        }  finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
    }

    public void updateUser(Long user_id, User user) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public User getUserById(Long user_id) throws SQLException {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = (User) session.load(User.class, user_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

    public List<User> getAllUsers() throws SQLException {
        Session session = null;
        List users = new ArrayList<User>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            users = session.createCriteria(User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return users;
    }

    public void deleteUser(User user) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


}