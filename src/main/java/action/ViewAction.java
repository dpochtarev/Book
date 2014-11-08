package action;


import book.User;
import form.SearchForm;
import logic.Book;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

        if(form!=null)  {
        SearchForm searchForm = (SearchForm)form;
            String str = searchForm.getStr();
        if(str!=null && !"".equals(str)) {

            try{
            List<User> list = Book.getInstance().getUserDAO().searchUsers(str);
            request.setAttribute("List", list);
            searchForm.resetForm();
            } catch (Exception e) {e.printStackTrace();}

        }  else {
            
        request.setAttribute("List", (Book.getList()));
        }
        }  else
        request.setAttribute("List", (Book.getList()));


        return mapping.findForward("success");
    }
    
}
