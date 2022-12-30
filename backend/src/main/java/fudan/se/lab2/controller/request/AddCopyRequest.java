package fudan.se.lab2.controller.request;

import java.util.List;

public class AddCopyRequest {
    String isbn;
    private int num;
    private  int aid;

    public AddCopyRequest(){}

    public AddCopyRequest(String isbn,int aid,int num){
        this.isbn = isbn;
        this.aid = aid;
        this.num = num;
    }

    public String getIsbn(){return this.isbn;}

    public void setIsbn(String isbn){this.isbn = isbn;}

    public int getAid(){return aid;}

    public void setAid(int aid){this.aid = aid;}

    public int getNum(){return this.num;}

    public void setNum(int num){this.num = num;}
}
