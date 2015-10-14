package servlets;
import book.User;
import logic.Book;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Deprecated
public class Handler extends HttpServlet{



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String page = "/index.jsp";

        String adddata = "insert into gr_user (user_name, user_phone, user_address) values ('" + name + "', '" +phone+ "', '" +address+"')";


//        JdbcHandler.createDb();

        System.out.println(name);
        System.out.println(phone);
        System.out.println(address);
        if(!name.equals("") && !name.equals(null)) {
        // Записываю юзера в базу.
         User user = new User(name, phone, address);
         try{
         Book.getInstance().getUserDAO().addUser(user);
         }catch (SQLException e) {
             e.printStackTrace();
         }   }

        // Дергаем данные из базы и заполняем список для странички

        ServletContext sc = getServletContext();
        RequestDispatcher dispatcher = sc.getRequestDispatcher(page);

        request.setAttribute("List", Book.getTable(Book.getList()));

               if(dispatcher!=null) {
                   dispatcher.forward(request, response);
               }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request, response);

    }

}
