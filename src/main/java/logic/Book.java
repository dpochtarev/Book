package logic;
import DAO.Impl.UserDAOImpl;
import DAO.UserDAO;
import book.User;
import util.LuceneSearchUtil;

import java.io.IOException;
import java.sql.SQLException;
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

    public void addUser(User user) throws SQLException, IOException {
        Book.getInstance().getUserDAO().addUser(user);
        LuceneSearchUtil.getInstance().addDoc(user);
    }

    public void editUser(Long id,User user) throws SQLException, IOException {
        Book.getInstance().getUserDAO().updateUser(id, user);
        LuceneSearchUtil.getInstance().reindex(user);
    }

    public void deleteUser(User user) throws SQLException, IOException {
        Book.getInstance().getUserDAO().deleteUser(user);
        LuceneSearchUtil.getInstance().delete(user);
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

   @Deprecated
   public static String getTable(List<User> list){
        
    String table = null;
       String btn = "";
       if(list!=null) {
//           table="<table align=\"center\"> <font SIZE=\"12\"><tr><td>Name</td><td>Phone</td><td>Address</td><td></td></tr></font>";
           for(User user:list){
//               System.out.printf("<input type=\"button\" onClick=\"edit(%s, %s, %s, %s)\" value=\"edit\" />", user.getId(), user.getName(), user.getPhone(), user.getAddress());
//               btn = String.format("<input type=\"button\" onClick=\"edit(%s, %s, %s, %s)\" value=\"edit\" />", user.getId(), user.getName(), user.getPhone(), user.getAddress());
               table = String.format("%s<tr><td>%s</td><td>%s</td><td>%s</td><td></td></tr>", table, user.getName(), user.getPhone(), user.getAddress());
           }
          table = String.format("%s</table>", table);
       }
       
        return table;
    }   

}
