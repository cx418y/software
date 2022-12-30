package fudan.se.lab2.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message+"");
    }

}
