package fudan.se.lab2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author LBW
 */
@Entity
@Table(name = "user")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class User implements Serializable {

    private static final long serialVersionUID = -6140085056226164016L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="aid")
    private Authority authority;
    public Authority getAuthority() {
        return authority;
    }
    public void setAuthority(Authority authority) {
        this.authority= authority;
    }

    @Column(unique = true)
    private String username;
    private String password;
    private String email;

    private int borrowNum;
    private int reserveNum;

    private int credit;


    //@ManyToOne(targetEntity=Authority.class,fetch=FetchType.LAZY)
    //@JoinColumn(name="authority",nullable=false,referencedColumnName="authority"

   // @OneToMany(mappedBy = "username",targetEntity = Reserved.class,cascade = CascadeType.ALL)
   // private List<Reserved> reserved;//预约记录


    public User() {}
    public User(String username, String password, String email, Authority authority) {
        this.username = username;
        this.password= password;
        this.email = email;
        this.authority = authority;
    }

    public User(String username, String password, String email, Authority authority,int borrowNum,int reserveNum) {
        this.username = username;
        this.password= password;
        this.email = email;
        this.authority = authority;
        this.borrowNum = borrowNum;
        this.reserveNum = reserveNum;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBorrowNum(){return this.borrowNum;}

    public void setBorrowNum(int borrowNum){this.borrowNum = borrowNum;}

    public int getReserveNum(){return this.reserveNum;}

    public void setReserveNum(int reserveNum){this.reserveNum = reserveNum;}

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
