package action;


import book.User;
import form.UserForm;
import logic.Book;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.SQLException;

public class AjaxAction extends Action {

    public ActionForward execute(ActionMapping mapping,ActionForm form,
                                 HttpServletRequest request,HttpServletResponse response)
            throws Exception {

        UserForm userForm =(UserForm) form;

        Long id=0l;
        String name = userForm.getName();
        String phone = userForm.getPhone();
        String address = userForm.getAddress();
        if(userForm.getId()!=null)    id = Long.valueOf(userForm.getId());


        System.out.printf("id : %s name :%s phone :%s address :%s\n", id, name, phone, address);

        if(id==0l) addUser(name, phone, address);
        else editUser(name, phone, address, id);

        userForm.resetform();

//        response.setContentType("text/text;charset=utf-8");
//        response.setHeader("cache-control", "no-cache");
//        PrintWriter out = response.getWriter();
//        System.out.println(Book.getTable(Book.getList()));
//        out.println(Book.getTable(Book.getList()));
//        out.flush();
        request.getSession().setAttribute("List", Book.getList());

        return mapping.findForward("users");

    }


    public void addUser(String name, String phone, String address) {
        if(!"".equals(name)  && name!=null) {
            // Записываю юзера в базу.
            User user = new User(name, phone, address);
            try{
                Book.getInstance().getUserDAO().addUser(user);
            }catch (SQLException e) {
                e.printStackTrace();
            }   }
    }
    public void editUser(String name, String phone, String address, Long id) {
        if(!"".equals(name)  && name!=null) {
            // Записываю изменения в базу.
            User user = new User(name, phone, address, id);
            try{
                Book.getInstance().getUserDAO().updateUser(id, user);
            }catch (SQLException e) {
                e.printStackTrace();
            }   }
    }
}
