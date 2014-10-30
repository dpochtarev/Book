package action;


import book.User;
import form.SearchForm;
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
import java.util.List;

public class ViewAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

        if(form!=null)  {
        SearchForm searchForm = (SearchForm)form;
            String str = searchForm.getStr();
        System.out.println(searchForm);
        if(str!=null && !"".equals(str)) {
            List<User> list = new ArrayList<>();
           for(User usr:Book.getList()) {
              if(usr.getName().contains(str) || usr.getAddress().contains(str) || usr.getPhone().contains(str))  list.add(usr);
           }
            request.setAttribute("List", list);
            searchForm.resetForm();
        }  else {
            
        request.setAttribute("List", (Book.getList()));
        }
        }  else
        request.setAttribute("List", (Book.getList()));


        return mapping.findForward("success");
    }
    
}
