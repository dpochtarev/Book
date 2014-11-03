package logic;
import DAO.Impl.UserDAOImpl;
import DAO.UserDAO;
import book.User;

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

   public static String getTable(List<User> list){
        
    String table = null;
       if(list!=null) {
           table="<table border=\"1\"> <font SIZE=\"12\"><tr><td>Name</td><td>Phone</td><td>Address</td></tr></font>";
           for(User user:list){
               table = String.format("%s<tr><td>%s</td><td>%s</td><td>%s</td></tr>", table, user.getName(), user.getPhone(), user.getAddress());
           }
          table = String.format("%s</table>", table);
       }
       
        return table;
    }   

}
