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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class FIndUser extends Action {
    public ActionForward execute(ActionMapping mapping,ActionForm form,
                                 HttpServletRequest request,HttpServletResponse response)
            throws Exception {

        UserForm userForm =(UserForm) form;
        String name = userForm.getName();
        String phone = userForm.getPhone();
        String address = userForm.getAddress();

        Collection users = Book.getInstance().getUserDAO().getAllUsers();
        Iterator iterator = users.iterator();
        while (iterator.hasNext())  {
            User usr = (User)iterator.next();
            if(usr.getName().equals(name) && usr.getAddress().equals(address) && usr.getPhone().equals(phone)) usr.getId();
        }
        request.setAttribute("List", Book.getTable(Book.getList()));

        form.reset(mapping, request);

        return mapping.findForward("success");
    }


}
