package DAO;

import main.resources.User;

import java.util.Collection;
import java.sql.SQLException;

public interface UserDAO {
    public void addUser(User user) throws SQLException;
    public void updateUser(Long bus_id, User user) throws SQLException;
    public User getUserById(Long bus_id) throws SQLException;
    public Collection getAllUsers() throws SQLException;
    public void deleteUser(User user) throws SQLException;

       }