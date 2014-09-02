package book;

public class Userdata {
    String name;
    String address;
    String phone;

        public String getName(){
        if(name!=null)return name;
            else return "";
    }
    public String getAddress(){
        if (address!=null) return address;
        else return "";
    }

     public String getPhone(){

         if (phone!=null) return phone;
         else return "";
     }

}
