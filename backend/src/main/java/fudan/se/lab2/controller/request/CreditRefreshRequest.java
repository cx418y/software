package fudan.se.lab2.controller.request;

public class CreditRefreshRequest {
    private String username;
    private String adminName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminName() {
        return adminName;
    }
}
