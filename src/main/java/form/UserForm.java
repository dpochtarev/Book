package form;

import org.apache.struts.action.ActionForm;


public class UserForm extends ActionForm{

    String name;
    String address;
    String phone;
    Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId(){
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
        
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void resetform() {

        setName(null);
        setAddress(null);
        setPhone(null);
        setId(0l);
       }

    }
