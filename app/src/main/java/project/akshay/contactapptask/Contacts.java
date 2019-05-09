package project.akshay.contactapptask;

public class Contacts {

    public String email;
    public String mobile;
    public String address;
    public String dob;
    public String name;
    public String picId;

    String getName(){
        return name;
    }

    public Contacts(String name, String email, String mobile, String dob, String address, String picId){
        this.name = name;
        this.address = address;
        this.email = email;
        this.dob = dob;
        this.mobile = mobile;
        this.picId = picId;
    }

    public Contacts(String name, String picId){
        this.picId = picId;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getDob() {
        return dob;
    }

    public String getPicId() {
        return picId;
    }

}
