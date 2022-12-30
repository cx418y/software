package fudan.se.lab2.Assist;


public class UserBookInfo {
    private String title;
    private String bookState;
    private String reserveTime;
    private String borTime;
    private String backTime;

    public UserBookInfo(){}
    public UserBookInfo(String title,String bookState,String reserveTime,String borTime,String backTime){
        this.title = title;
        this.bookState = bookState;
        this.reserveTime = reserveTime;
        this.borTime = borTime;
        this.backTime = backTime;
    }

    public String getTitle(){return this.title;}

    public void setTitle(String title){this.title = title;}

    public String getBookState(){return this.bookState;}

    public void setBookState(String bookState){this.bookState = bookState;}

    public String getReserveTime(){return this.reserveTime;}

    public void setReserveTime(String reserveTime){this.reserveTime= reserveTime;}

    public String getBorTime(){return this.borTime;}

    public void setBorTime(String borTime){this.borTime = borTime;}

    public String getBackTime(){return this.backTime;}

    public void setBackTime(String backTime){this.backTime = backTime;}




}
