package form;

import org.apache.struts.action.ActionForm;

public class SearchForm extends ActionForm{
    
    String str;

    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        return "SearchForm{" +
                "str='" + str + '\'' +
                '}';
    }

    public void setStr(String str) {
        this.str = str;
    }

     public void resetForm(){
         setStr(null);
     }
    
}
