package fudan.se.lab2.controller.request;

import java.util.Set;

/**
 * @author LBW
 */
public class RegisterRequest {
    private String userName;
    private String password;
    private String email;
    private String input;
    private String authority;

    public RegisterRequest() {}

    public RegisterRequest(String userName, String password, String email, String authority) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.authority = authority;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getInput(){return input;}
}

