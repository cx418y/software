package fudan.se.lab2.Assist;

import fudan.se.lab2.domain.Area;

public class UserFineRecord {
    String time;
    double price;
    String adminName;
    String isbn;
    Area area;
    String reason;
    String paid;

    public UserFineRecord(){}

    public UserFineRecord(String time,double price,String adminName,String isbn,Area area,String reason,String paid){
        this.time = time;
        this.price = price;
        this.adminName = adminName;
        this.isbn = isbn;
        this.area = area;
        this.reason = reason;
        this.paid = paid;
    }

    public String getTime(){return this.time;}

    public void setTime(String time){this.time = time;}

    public String getAdminName(){return this.adminName;}

    public void setAdminName(String adminName){this.adminName = adminName;}

    public String getIsbn(){return this.isbn;}

    public void setIsbn(String isbn){this.isbn = isbn;}

    public Area getArea(){return this.area;}

    public void setArea(Area area){this.area = area;}

    public String getPaid(){return this.paid;}

    public void setPrice(String paid){this.paid = paid;}

    public String getReason(){return this.reason;}

    public void setReason(String reason){this.reason = reason;}

    public double getPrice(){return this.price;}

    public void setPrice(double price){this.price = price;}


}
