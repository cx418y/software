package fudan.se.lab2.controller.request;

import fudan.se.lab2.domain.Area;

public class BorrowBookRequest {

    private String userName;
    private String adminName;
    private String[] isbns;
    private int aid;

    public BorrowBookRequest(){}

    public BorrowBookRequest(String userName, String adminName,String[] isbns,int aid){
        this.userName = userName;
        this.adminName = adminName;
        this.isbns = isbns;
        this.aid = aid;
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

    public String[] getIsbns() {
        return this.isbns;
    }

    public void setIsbns(String[] isbns) { this.isbns = isbns; }

    public int getAid(){return this.aid;}

    public void setAid(int aid){this.aid = aid;}
}
