package fudan.se.lab2.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fine")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Fine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String username;
    private String adminName;
    private double price;
    private int paid;
    private String payTime;
    private String reason;
    private String startTime;
    private String isbn;

    @ManyToOne
    @JoinColumn(name="aid")
    private Area area;
    public Area getArea() {
        return area;
    }
    public void setArea(Area area) {
        this.area = area;
    }

    public Fine(){}

    public Fine(String username,String adminName,String isbn,String reason,double price,Area area,String startTime){
        this.username = username;
        this.adminName = adminName;
        this.isbn = isbn;
        this.reason = reason;
        this.price = price;
        this.area =area;
        this.startTime = startTime;

    }

    public String getUsername(){return username;}

    public void setUsername(String username){this.username = username;}

    public String getAdminName(){return adminName;}

    public void setAdminName(String adminName){this.adminName = adminName;}

    public double getPrice(){return price;}

    public void setPrice(double price){this.price = price;}

    public int getPaid(){return paid;}

    public void setPaid(int paid){this.paid = paid;}

    public String getStartTime(){return startTime;}

    public void setStartTime(String startTime){this.startTime= startTime;}

    public String getPayTime(){return payTime;}

    public void setPayTime(String payTime){this.payTime = payTime;}

    public String getReason(){return this.reason;}

    public void setReason(String reason){this.reason = reason;}

    public String getIsbn(){return this.isbn;}

    public void setIsbn(String isbn){this.isbn = isbn;}







}
