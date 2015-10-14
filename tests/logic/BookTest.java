package logic;


import book.User;

import java.sql.SQLException;
import java.util.List;

public class BookTest {
    public static void main(String[] args) throws SQLException {
//        List<User> list = Book.getList();
        List<User> list = Book.getList();
        for (User usr:list) System.out.println(usr.getName()+" "+usr.getAddress());
    }
}
