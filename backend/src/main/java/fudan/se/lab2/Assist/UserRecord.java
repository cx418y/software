package fudan.se.lab2.Assist;

import fudan.se.lab2.domain.Area;

public class UserRecord {
    String time;
    String operation;
    String adminName;
    String isbn;
    Area area;

    public UserRecord(){}
    public UserRecord(String time, String operation, String adminName,String isbn,Area area){
        this.time = time;
        this.operation = operation;
        this.adminName = adminName;
        this.isbn = isbn;
        this.area = area;
    }

    public String getTime(){return this.time;}

    public void setTime(String time){this.time = time;}

    public String getOperation(){return  this.operation;}

    public void setOperation(String operation){this.operation = operation;}

    public String getAdminName(){return this.adminName;}

    public void setAdminName(String adminName){this.adminName = adminName;}

    public String getIsbn(){return this.isbn;}

    public void setIsbn(String isbn){this.isbn = isbn;}

    public Area getArea(){return this.area;}

    public void setArea(Area area){this.area = area;}

}
