package fudan.se.lab2.Assist;

public class UserInfo {
    private String username;
    private String email;
    private String authority;
    private int mostBorrow;
    private long borrowOvertime;
    private long reserveOvertime;
    int credit;
    double fine;

    public UserInfo(){}

    public UserInfo(String username,String email,String authority,int mostBorrow,long borrowOvertime,long reserveOvertime,int credit,double fine){
        this.username = username;
        this.email = email;
        this.authority = authority;
        this.mostBorrow = mostBorrow;
        this.borrowOvertime = borrowOvertime;
        this.reserveOvertime = reserveOvertime;
        this.credit = credit;
        this.fine = fine;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public int getMostBorrow() {
        return mostBorrow;
    }

    public void setMostBorrow(int mostBorrow) {
        this.mostBorrow = mostBorrow;
    }

    public long getBorrowOvertime() {
        return borrowOvertime;
    }

    public void setBorrowOvertime(long borrowOvertime) {
        this.borrowOvertime = borrowOvertime;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public long getReserveOvertime() {
        return reserveOvertime;
    }

    public void setReserveOvertime(long reserveOvertime) {
        this.reserveOvertime = reserveOvertime;
    }
}
