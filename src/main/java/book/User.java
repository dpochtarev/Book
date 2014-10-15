package book;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;


public class User {
    
    private Long id;
    private String name, phone, address;


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public User(){

    }
    public User(String name, String phone, String address){
          this.name = name;
        this.phone = phone;
        this.address = address;
    }


}
