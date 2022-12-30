package fudan.se.lab2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * @author LBW
 */
@Entity
public class Authority implements GrantedAuthority {

    private static final long serialVersionUID = -8974777274465208640L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String authority;

    private int mostBorrow;
    private long borrowOvertime;
    private long reserveOvertime;

    //@OneToMany(mappedBy = "authority",targetEntity = User.class,cascade = CascadeType.ALL)
    //@JsonIgnore
  //  private Set<User> users;

    public Authority() {

    }

    public Authority(String authority) {
        this.authority = authority;
    }
    public Authority(int mostBorrow, long borrowOvertime,long reserveOvertime){
        this.mostBorrow = mostBorrow;
        this.borrowOvertime = borrowOvertime;
        this.reserveOvertime = reserveOvertime;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public int getMostBorrow(){return mostBorrow;}

    public void setMostBorrow(int mostBorrow){this.mostBorrow = mostBorrow;}

    public long getBorrowOvertime(){return borrowOvertime;}

    public void setBorrowOvertime(long borrowOvertime){this.borrowOvertime = borrowOvertime;}

    public long getReserveOvertime(){return reserveOvertime;}

    public void setReserveOvertime(long reserveOvertime){this.reserveOvertime = reserveOvertime;}

}
