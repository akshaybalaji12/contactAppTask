package project.akshay.contactapptask;

public class Contacts {

    public String email;
    public String mobile;
    public String address;
    public String dob;
    public String name;
    public int picId;

    String getName(){
        return name;
    }

    public Contacts(String name, String email, String mobile, String dob, String address, int picId){
        this.name = name;
        this.address = address;
        this.email = email;
        this.dob = dob;
        this.mobile = mobile;
        this.picId = picId;
    }

    public Contacts(String name, int picId){
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

    public int getPicId() {
        return picId;
    }

}
