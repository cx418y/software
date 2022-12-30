package fudan.se.lab2.controller.request;

public class ForgetRequest {
    public ForgetRequest(){}
    private String username;
    private String input;

    public ForgetRequest(String username, String input){
        this.username = username;
        this.input = input;
    }

    public String getUsername() {
        return username;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
