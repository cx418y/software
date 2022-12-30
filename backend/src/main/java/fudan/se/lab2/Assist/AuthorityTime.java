package fudan.se.lab2.Assist;

public class AuthorityTime {

    private int num;
    private int[] reserveTime;
    private int[]borrowTime;

    public AuthorityTime(){}

    public AuthorityTime(int num,int[]reserveTime,int[]borrowTime){
        this.num = num;
        this.reserveTime = reserveTime;
        this.borrowTime = borrowTime;
    }

    public int getNum(){return this.num;}

    public void setNum(int num){this.num = num;}

    public int[] getReserveTime(){return this.reserveTime;}

    public void setReserveTime(int[]reserveTime){this.reserveTime = reserveTime;}

    public int[] getBorrowTime(){return this.borrowTime;}

    public void setBorrowTime(int[]borrowTime){this.borrowTime = borrowTime;}
}
