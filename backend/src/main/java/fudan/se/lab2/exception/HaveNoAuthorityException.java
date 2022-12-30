package fudan.se.lab2.exception;

public class HaveNoAuthorityException extends RuntimeException {
    public HaveNoAuthorityException(String s) {
        super(s);
    }
}
