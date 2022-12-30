package fudan.se.lab2.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userName) {
        super(userName+" not found!");
    }
}
