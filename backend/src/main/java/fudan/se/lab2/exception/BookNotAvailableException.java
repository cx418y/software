package fudan.se.lab2.exception;

public class BookNotAvailableException extends RuntimeException {
//    private static final long serialVersionUID = -6074753940710869977L;

    public BookNotAvailableException(String isbn) {
        super("ISBN '" + isbn + "' isn't available");
    }

}
