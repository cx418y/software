package fudan.se.lab2.exception;

public class UserCreditNotEnoughException extends RuntimeException {
    public UserCreditNotEnoughException(String message){
        super(message);
    }

}
