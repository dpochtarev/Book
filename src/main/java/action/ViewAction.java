package action;


import book.User;
import form.SearchForm;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import util.LuceneSearchUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewAction extends Action {

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException, ParseException {
        LuceneSearchUtil instance = LuceneSearchUtil.getInstance();

        if(form!=null) {
            SearchForm searchForm = (SearchForm) form;
            String str = searchForm.getStr();
            if (str != null ) {
                try {
//                    List<User> list = Book.getInstance().getUserDAO().searchUsers(str);
//                    request.setAttribute("List", list);
                    List<User> list = instance.search(str);
                    request.getSession().setAttribute("List", list);
                    searchForm.resetForm();
                    return  mapping.findForward("users");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else
                request.getSession().setAttribute("List", instance.search(""));
//                request.setAttribute("List", instance.search(""));
        } else
            request.getSession().setAttribute("List", instance.search(""));
        return mapping.findForward("success");
    }
}
    

