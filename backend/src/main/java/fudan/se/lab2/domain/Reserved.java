package fudan.se.lab2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reserved")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Reserved implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    private String startTime;
    private String endTime;
    private int borrow;
    private int overtime;

    @ManyToOne(targetEntity = Book.class,fetch=FetchType.LAZY)
    @JoinColumn(name = "isbn", nullable = false, referencedColumnName = "isbn")
    private Book book;

    //@ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY)
    // @JoinColumn(name = "username",nullable = false, referencedColumnName = "username")
    private String username;

    public Reserved(){}

    public Reserved(String startTime, String endTime, String username, Book book,int borrow,int overtime) {
        this.book = book;
        this.startTime = startTime;
        this.endTime = endTime;
        this.username = username;
        this.borrow = borrow;
        this.overtime = overtime;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Book getBook() {
        return book;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getBorrow(){return borrow;}

    public void setBorrow(int borrow){this.borrow = borrow;}

    public int getOvertime(){return overtime;}

    public void setOvertime(int overtime){this.overtime = overtime;}

}
