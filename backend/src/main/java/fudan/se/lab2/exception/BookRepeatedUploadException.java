package fudan.se.lab2.exception;

public class BookRepeatedUploadException extends RuntimeException {

    public BookRepeatedUploadException() {
        super("Book has been uploaded repeatedly");
    }
}


