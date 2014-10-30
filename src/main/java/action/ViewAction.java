package action;


import logic.Book;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){


        System.out.println( "form ="+ form);

        request.setAttribute("List", (Book.getList()));
        request.setAttribute("value", "add");
        return mapping.findForward("success");
    }
    
}
