package fudan.se.lab2.exception;

public class InformationMissingException extends RuntimeException {
    public InformationMissingException(String s) {
        super(s+" missing!");
    }
}
