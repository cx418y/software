package fudan.se.lab2.Assist;

import fudan.se.lab2.domain.BookState;

import java.io.Serializable;

public class BookCopies implements Serializable {
    private String isbn;
    private BookState bookState;
    private String areaName;
    private String userName;
    private String adminName;

    public BookCopies(){}

    public BookCopies(String isbn,BookState bookState,String areaName,String userName,String adminName){
        this.isbn = isbn;
        this.bookState = bookState;
        this.areaName = areaName;
        this.userName = userName;
        this.adminName = adminName;
    }

    public void setIsbn(String isbn){ this.isbn = isbn; }

    public String getIsbn(){return isbn;}

    public BookState getBookState() {
        return bookState;
    }

    public void setBookState(BookState bookState) {
        this.bookState = bookState;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setArea(String areaName) {
        this.areaName = areaName;
    }

    public void setUserName(String userName){ this.userName = userName; }

    public String getUserName(){return userName;}

    public void setAdminName(String adminName){ this.adminName = adminName; }

    public String getAdminName(){return adminName;}
}
