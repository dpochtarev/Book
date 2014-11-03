package DAO;

import book.User;

import java.util.Collection;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    public void addUser(User user) throws SQLException;
    public void updateUser(Long user_id, User user) throws SQLException;
    public User getUserById(Long user_id) throws SQLException;
    public Collection getAllUsers() throws SQLException;
    public void deleteUser(User user) throws SQLException;
    public List<User> searchUsers(String param) throws SQLException;

       }