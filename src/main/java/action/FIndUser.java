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

public class FIndUser extends Action {
    public ActionForward execute(ActionMapping mapping,ActionForm form,
                                 HttpServletRequest request,HttpServletResponse response)
            throws Exception {


        Long id = Long.valueOf(request.getParameter("id"));
        boolean edit = Boolean.valueOf(request.getParameter("edit"));
        User user = Book.getInstance().getUserDAO().getUserById(id);


        if(edit){

            ((UserForm)form).setName(user.getName());
            ((UserForm)form).setPhone(user.getPhone());
            ((UserForm)form).setAddress(user.getAddress());
            ((UserForm)form).setId(user.getId());
        }
        else{
            Book.getInstance().getUserDAO().deleteUser(user);
            ((UserForm)form).resetform();
        }
        return mapping.findForward("success");
    }


}
