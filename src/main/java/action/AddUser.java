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
import java.sql.SQLException;

public class AddUser extends Action {

    public ActionForward execute(ActionMapping mapping,ActionForm form,
                                 HttpServletRequest request,HttpServletResponse response)
            throws Exception {

        UserForm userForm =(UserForm) form;
        String name = userForm.getName();
        String phone = userForm.getPhone();
        String address = userForm.getAddress();

        if(!"".equals(name)  && name!=null) {
            // Записываю юзера в базу.
            User user = new User(name, phone, address);
            try{
                Book.getInstance().getUserDAO().addUser(user);
            }catch (SQLException e) {
                e.printStackTrace();
            }   }

        userForm.resetform();

        return mapping.findForward("success");
    }


}
