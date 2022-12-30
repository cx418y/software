package fudan.se.lab2.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
//import java.sql.Date;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.*;


/**
 * @author LBW
 */
@Entity
@Table(name = "borrow")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Borrow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="aid")
    private Area area;
    public Area getArea() {
        return area;
    }
    public void setArea(Area area) {
        this.area = area;
    }

   // @ManyToOne
    //@JoinColumn(name="isbn")
    @ManyToOne(targetEntity = Book.class,fetch=FetchType.LAZY)
    @JoinColumn(name = "isbn", nullable = false, referencedColumnName = "isbn")
    private Book book;
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    /*
    @OneToOne(targetEntity = Comment.class,fetch=FetchType.LAZY)
    @JoinColumn(name = "comment", nullable = false, referencedColumnName = "id")
    private Comment comment;
    public Comment getComment() {
        return comment;
    }
    public void setComment(Comment comment) {
        this.comment = comment;
    }

     */

    private String userName;
    private String adminName;
    private String borrowTime;
    private String endTime;
    private int reverse;
    private int overtime;

    public Borrow(){}
    public Borrow(Book book,Area area,String userName, String adminName, String borrowTime,String endTime){
        this.book = book;
        this.userName = userName;
        this.adminName = adminName;
        this.borrowTime = borrowTime;
        this.area = area;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAdminName() { return this.adminName; }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getBorrowTime() {
        return this.borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getEndTime(){return this.endTime;}

    public void setEndTime(String endTime){this.endTime = endTime;}

    public int getReverse(){return reverse;}

    public void setReverse(int reverse){this.reverse = reverse;}

    public int getOvertime(){return  overtime;}

    public void setOvertime(int overtime){this.overtime = overtime;}




}
