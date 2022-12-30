package fudan.se.lab2.controller.request;

public class PayRequest {
    private String[] time;
    private String[] isbn;
    private String userName;

    public PayRequest(){}
    public PayRequest(String[]time,String[] isbn,String userName){
        this.time = time;
        this.isbn = isbn;
        this.userName = userName;
    }
    public String[] getIsbn() {
        return isbn;
    }

    public String getUserName() {
        return userName;
    }

    public String[] getTime() {
        return time;
    }
}
