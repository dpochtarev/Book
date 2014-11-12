package book;
import java.sql.*;

public class JdbcHandler {

   static String user = "sa";//Логин пользователя
   static String password = "";//Пароль пользователя
   static String url = "jdbc:hsqldb:file:/../db/db;shutdown=true";//URL адрес
   static String driver = "org.hsqldb.jdbcDriver";


    public static Connection getConnection(){

//               Регистрирум драйвер
        Connection c = null;

        try{
            Driver _driver = (Driver)Class.forName(driver).newInstance();
            DriverManager.registerDriver(_driver);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.err.println("Can't load driver");
            e.printStackTrace();
            System.exit(1);
        }

//            Проверяем коннект
        try  {
            c = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Connection refused");
            e.printStackTrace();
        }
        return c;
    }


    public static void createDb(){

        Connection c = null;
        try{
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            c = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        boolean check=true;

        try{
            c = DriverManager.getConnection(url, user, password);
            Statement st = c.createStatement();
            st.executeQuery("select * from gr_user");
            System.out.println("Селект");
        }
        catch (SQLException e) {
           check = false;
            System.out.println("не пошло");

        }

          if(!check) {

              try{
                  executeQuery("CREATE TABLE gr_user (user_name VARCHAR(50)  NOT NULL, user_phone VARCHAR(50) NULL, user_address VARCHAR(50) NULL)");
                  System.out.println("создали таблицу");

              }
              catch (Exception e){
                  e.printStackTrace();
              }


          }



          }
    
    

    public static ResultSet getrs(String query) {
        ResultSet rs = null;
        Connection c = null;

        try{
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try{
            c = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }




        try{
            Statement st = c.createStatement();//Готовим запрос
            rs = st.executeQuery(query);//Выполняем запрос к БД, результат в переменной rs
        } catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public static void executeQuery(String query){

        Connection c = null;

        try{
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try{
            c = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


        try{
            c.createStatement().execute(query);

        } catch(Exception e){
            e.printStackTrace();

        }

    }
        

    
    
    
}