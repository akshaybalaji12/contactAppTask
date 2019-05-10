package project.akshay.contactapptask;

public class Contacts {

    public String mobile;
    public String name;
    public String picId;

    String getName(){
        return name;
    }

    public Contacts(String name, String mobile, String picId){
        this.name = name;
        this.mobile = mobile;
        this.picId = picId;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPicId() {
        return picId;
    }

}
