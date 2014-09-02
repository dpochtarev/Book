package logic;
import DAO.Impl.UserDAOImpl;
import DAO.UserDAO;
import main.resources.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class Book {

    private static UserDAO userDAO = null;
    private static Book instance = null;
    private static List<User> list = null;

    public static synchronized Book getInstance(){

        if(instance==null) {
            instance = new Book();
        }
     return instance;
    }

    public UserDAO getUserDAO(){

        if(userDAO == null) {
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }



   public static List<User> getList() {

       UserDAOImpl usr = new UserDAOImpl();
       try{
           list = usr.getAllUsers();


       } catch (Exception e){
           e.printStackTrace();
       }
      return list;

   }




}
