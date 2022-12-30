package fudan.se.lab2.controller.request;

import fudan.se.lab2.domain.Area;

public class ReturnBookRequest {
    private String userName;
    private String adminName;
    private String[] isbns;
    private int returnAid;
    private String[] states;

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

    public int getReturnAid(){return this.returnAid;}

    public void setReturnAid(int returnAid){this.returnAid = returnAid;}

    public String[] getStates(){return this.states;}

    public void setStates(String[] states){this.states = states;}

}
