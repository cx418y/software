package fudan.se.lab2.Assist;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Entity;
import java.io.Serializable;
//@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class RemindRecord implements Serializable {
   private String userName;
   private String[] reserveOvertime;
   private String[] borrowOvertime;
   private String[] unpaid;


    public RemindRecord(){}

    public RemindRecord(String userName, String[] reserveOvertime, String[] borrowOvertime, String[] unpaid){
       this.unpaid = unpaid;
       this.borrowOvertime = borrowOvertime;
       this.reserveOvertime = reserveOvertime;
       this.userName = userName;
    }

    public String[] getBorrowOvertime() {
        return borrowOvertime;
    }

    public String[] getReserveOvertime() {
        return reserveOvertime;
    }

    public String getUserName() {
        return userName;
    }

    public String[] getUnpaid() {
        return unpaid;
    }
}
