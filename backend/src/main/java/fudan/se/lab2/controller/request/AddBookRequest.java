package fudan.se.lab2.controller.request;

import java.util.List;

public class AddBookRequest {
    private String cover;
    private String title;
    private String author;
    private String date;
    private String isbn;
    private String intro;
    private int aid;
    private List<Integer> num;
    private int cid;
    private double price;

    public AddBookRequest(){}

    public AddBookRequest(String cover,String title,String author,String date,String isbn,String intro,int aid,List<Integer> num,int cid,double price){
        this.cover = cover;
        this.title = title;
        this.author = author;
        this.date = date;
        this.isbn = isbn;
        this.intro = intro;
        this.aid = aid;
        this.num = num;
        this.cid = cid;
        this.price = price;
    }

    public String getCover(){ return this.cover;}

    public void setCover(String cover){this.cover = cover;}

    public String getTitle(){return this.title;}

    public void setTitle(String title){this.title = title;}

    public String getAuthor(){return this.author;}

    public void setAuthor(String author){this.author = author;}

    public String getDate(){return this.date;}

    public void setDate(String date){this.date = date;}

    public String getIsbn(){return this.isbn;}

    public void setIsbn(String isbn){this.isbn = isbn;}

    public String getIntro(){return this.intro;}

    public void setIntro(String intro){this.intro = intro;}

    public int getAid(){return this.aid;}

    public void setAid(int aid){this.aid = aid;}

    public List<Integer> getNum(){return this.num;}

    public void setNum(List<Integer> num){this.num = num;}

    public int getCid(){return this.cid;}

    public void setCid(int cid){this.cid = cid;}

    public double getPrice(){return this.price;}

    public void setPrice(double price){this.price = price;}



}
