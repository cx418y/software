package fudan.se.lab2.Assist;

import fudan.se.lab2.domain.Area;

public class UserReserveNotFetch {

    private String isbn;
    private String title;
    private Area area;
    private String time;
    private String expireTime;

    public UserReserveNotFetch(){}

    public UserReserveNotFetch(String isbn,String title,Area area,String time,String expireTime){
        this.isbn = isbn;
        this.title = title;
        this.area = area;
        this.time = time;
        this.expireTime = expireTime;
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
}
