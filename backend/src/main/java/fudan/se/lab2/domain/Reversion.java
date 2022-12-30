package fudan.se.lab2.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;
import javax.persistence.*;


/**
 * @author LBW
 */
@Entity
@Table(name = "reversion")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Reversion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @ManyToOne
    @JoinColumn(name="borrowAid")
    private Area borrowArea;
    public Area getBorrowArea() {
        return borrowArea;
    }
    public void setBorrowArea(Area borrowArea) {
        this.borrowArea = borrowArea;
    }

    @ManyToOne
    @JoinColumn(name="returnAid")
    private Area returnArea;
    public Area getReturnArea() {
        return returnArea;
    }
    public void setReturnArea(Area returnArea) {
        this.returnArea = returnArea;
    }

    @ManyToOne(targetEntity = Book.class,fetch=FetchType.LAZY)
    @JoinColumn(name = "isbn", nullable = false, referencedColumnName = "isbn")
    private Book book;
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    public Reversion(){}
    public Reversion( Book book,String borrowUsername,String returnUsername,String borrowAdmin, String returnAdmin,String borrowTime, String returnTime, Area borrowArea, Area returnArea,String state){
        this.book = book;
        this.borrowUsername = borrowUsername;
        this.returnUsername = returnUsername;
        this.borrowAdmin = borrowAdmin;
        this.returnAdmin = returnAdmin;
        this.borrowTime = borrowTime;
        this.returnTime = returnTime;
        this.borrowArea = borrowArea;
        this.returnArea = returnArea;
        this.state = state;
      //  this.overtime = overtime;
    }

    String borrowUsername;
    String returnUsername;
    String borrowAdmin;
    String borrowTime;
    String returnTime;
    String returnAdmin;
    int overtime;
    String state;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBorrowUsername() {
        return this.borrowUsername;
    }

    public void setBorrowUsername(String borrowUsername) {
        this.borrowUsername = borrowUsername;
    }

    public String getReturnUsername(){return this.returnUsername;}

    public void setReturnUsername(String returnUsername){this.returnUsername = returnUsername;}

    public String getBorrowAdmin() { return this.borrowAdmin; }

    public void setBorrowAdmin(String borrowAdmin) {
        this.borrowAdmin = borrowAdmin;
    }

    public String getReturnAdmin(){return returnAdmin;}

    public void setReturnAdmin(String returnAdmin){this.returnAdmin = returnAdmin;}


    public String getBorrowTime() {
        return this.borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getReturnTime() {
        return this.returnTime;
    }

    public void setReturnTime(String returnTime) { this.returnTime = returnTime; }

    public int getOvertime(){return this.overtime;}

    public void setOvertime(int overtime){this.overtime = overtime;}

    public String getState(){return state;}

    public void setState(String state){this.state = state;}


}
