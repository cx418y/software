package fudan.se.lab2.Assist;

import fudan.se.lab2.domain.Area;

public class UserBorrowNotReturn {
    private String isbn;
    private String title;
    private String time;
    private String expireTime;
    private String adminName;
    private Area area;

    public UserBorrowNotReturn(){}

    public UserBorrowNotReturn(String isbn,String title,String time,String expireTime,Area area,String adminName){
        this.isbn = isbn;
        this.title = title;
        this.area = area;
        this.time = time;
        this.expireTime = expireTime;
        this.adminName = adminName;
    }


    public void setIsbn(String isbn){ this.isbn = isbn; }

    public String getIsbn(){return isbn;}

    public String getTitle(){return this.title;}

    public void setTitle(String title){this.title = title;}

    public Area getArea(){return this.area;}

    public void setArea(Area area){this.area = area;}

    public String getTime(){return this.time;}

    public void setTime(String time){this.time = time;}

    public String getExpireTime(){return this.expireTime;}

    public void setExpireTime(String expireTime){this.expireTime = expireTime;}

    public String getAdminName(){return this.adminName;}

    public void setAdminName(String adminName){this.adminName = adminName;}
}
