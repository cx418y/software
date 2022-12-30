package fudan.se.lab2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.expression.spel.ast.BooleanLiteral;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;


/**
 * @author LBW
 */
@Entity
@Table(name = "book")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })

public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name="cid")
    private Category category;


    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name="aid")
    private Area area;
    public Area getArea() {
        return area;
    }
    public void setArea(Area area) {
        this.area = area;
    }

    @ManyToOne
    @JoinColumn(name="sid")
    private BookState bookState;
    public BookState getBookState() {
        return bookState;
    }
    public void setBookState(BookState bookState) {
        this.bookState = bookState;
    }

    private String author;
    private String title;
    private String cover;
    private String intro;
    private String date;

    private String isbn;
    private int isCopy;
    private int number;
    private int commentNumber;
    private double rate;

    private double price;

    public Book(){}
    public Book(String cover,String title,String author,String date,String intro,double price){
        this.author = author;
        this.title = title;
        this.cover = cover;
        this.intro = intro;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author){this.author = author;}

    public String getAuthor(){return author;}

    public void setCover(String cover){ this.cover = cover; }

    public String getCover(){return cover;}

    public void setIntro(String intro){ this.intro = intro; }

    public String getIntro(){return intro;}

    public void setTitle(String title){ this.title = title; }

    public String getTitle(){return title;}

    public void setIsbn(String isbn){ this.isbn = isbn; }

    public String getIsbn(){return isbn;}

    public void setDate(String date){ this.date = date; }

    public String getDate(){return date;}

    public int getNumber(){return this.number;}

    public void setNumber(int num){this.number = num;}

    public int getIsCopy(){return this.isCopy;}

    public void setIsCopy(int isCopy){this.isCopy = isCopy;}

    public double getPrice(){return this.price;}

    public void setPrice(double price){this.price = price;}

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
