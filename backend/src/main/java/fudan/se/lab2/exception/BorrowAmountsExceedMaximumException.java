package fudan.se.lab2.exception;

public class BorrowAmountsExceedMaximumException extends RuntimeException{
    public BorrowAmountsExceedMaximumException(String username,int num){
        super("user "+username+" can borrow "+num+" books most!");
    }
}
