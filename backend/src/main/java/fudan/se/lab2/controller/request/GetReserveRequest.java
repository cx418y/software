package fudan.se.lab2.controller.request;

public class GetReserveRequest {
    private String[] isbn;
    private String adminName;
    private String userName;
    private int aid;

    public GetReserveRequest(String userName, int aid, String adminName, String[] isbn){
        this.adminName = adminName;
        this.userName = userName;
        this.aid = aid;
        this.isbn = isbn;
    }

    public String getUserName() {
        return userName;
    }

    public int getAid() {
        return aid;
    }

    public String getAdminName() {
        return adminName;
    }

    public String[] getIsbn() {
        return isbn;
    }
}

