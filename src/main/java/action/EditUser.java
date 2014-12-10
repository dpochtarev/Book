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

public class EditUser extends Action {
    public ActionForward execute(ActionMapping mapping,ActionForm form,
                                 HttpServletRequest request,HttpServletResponse response)
            throws Exception {


        Long id = Long.valueOf(request.getParameter("id"));
//        boolean edit = Boolean.valueOf(request.getParameter("edit"));
        User user = Book.getInstance().getUserDAO().getUserById(id);
//        UserForm userForm = (UserForm)form;
//        if(edit){
//
//            userForm.setName(user.getName());
//            userForm.setPhone(user.getPhone());
//            userForm.setAddress(user.getAddress());
//            userForm.setId(user.getId());
//        }
//        else{
            Book.getInstance().getUserDAO().deleteUser(user);
        request.getSession().setAttribute("List", Book.getList());


        return mapping.findForward("success");
    }




}
