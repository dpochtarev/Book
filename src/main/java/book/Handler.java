package book;


import DAO.Impl.UserDAOImpl;
import main.resources.User;
import logic.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class Handler extends HttpServlet{


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String table=null;
        String page = "index.jsp";

        String adddata = "insert into gr_user (user_name, user_phone, user_address) values ('" + name + "', '" +phone+ "', '" +address+"')";


//        JdbcHandler.createDb();

        System.out.println(name);
        System.out.println(phone);
        System.out.println(address);
        // Записываю юзера в базу.
         User user = new User(name, phone, address);
         try{
         Book.getInstance().getUserDAO().addUser(user);
         }catch (SQLException e) {
             e.printStackTrace();
         }

        // Дергаем данные из базы и заполняем список для странички

        request.setAttribute("List", Book.getList());
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
               if(dispatcher!=null) {
                   dispatcher.forward(request, response);
               }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request, response);

          response.sendRedirect("index.jsp");
    }





}
