package fudan.se.lab2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.omg.PortableInterceptor.SUCCESSFUL;

import javax.persistence.*;

@Entity
@Table(name = "credit_record")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class CreditRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /*
    @ManyToOne(targetEntity = User.class,fetch= FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false, referencedColumnName = "username")
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

     */



    public CreditRecord(){}

    public CreditRecord(String username,String adminName, int changes, String reason, String time, String isbn,int credit){
        this.username = username;
        this.adminName = adminName;
        this.changes = changes;
        this.reason = reason;
        this.time = time;
        this.isbn = isbn;
        this.credit = credit;
    }

    private String username;
    private  String adminName;
    private  int changes;
    private String reason;
    private String time;
    private String isbn;
    private int credit;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public int getChanges() {
        return changes;
    }

    public void setChanges(int changes) {
        this.changes = changes;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
