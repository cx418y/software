package fudan.se.lab2.controller.request;

public class ResetRequest {
    private String oldPass;
    private String newPass;
    private String username;

    public ResetRequest() {}
    public ResetRequest(String username, String oldPass, String newPass){
        this.username = username;
        this.newPass = newPass;
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public String getOldPass() {
        return oldPass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
