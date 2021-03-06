package action;


import book.User;
import form.UserForm;
import logic.Book;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import util.LuceneSearchUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
@Deprecated
public class AddUser extends Action {

    public ActionForward execute(ActionMapping mapping,ActionForm form,
                                 HttpServletRequest request,HttpServletResponse response)
            throws Exception {

//        UserForm userForm =(UserForm) form;
//        String name = userForm.getName();
//        String phone = userForm.getPhone();
//        String address = userForm.getAddress();
//        Long id = userForm.getId();

//        UserForm userForm =(UserForm) form;
//
//
//
//        String name = request.getParameter("name");
//        String phone = request.getParameter("phone");
//        String address = request.getParameter("address");
//        Long id = Long.valueOf(request.getParameter("id"));
//
//        System.out.printf("id : %s name :%s phone :%s address :%s", id, name, phone, address);
//
//        if(id==0l) addUser(name, phone, address);
//            else editUser(name, phone, address, id);
//
//        userForm.resetform();

        return mapping.findForward("success");
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
