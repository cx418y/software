package fudan.se.lab2.controller.request;

public class CommentRequest {
    private double rate;
    private String evaluation;
    private String isbn;
    private String userName;

    public CommentRequest(){}

    public CommentRequest(double rate, String evaluation, String isbn, String userName){
        this.rate = rate;
        this.evaluation = evaluation;
        this.isbn = isbn;
        this.userName = userName;
    }


    public String getUserName() {
        return userName;

    }

    public String getIsbn() {
        return isbn;
    }

    public double getRate() {
        return rate;
    }

    public String getEvaluation() {
        return evaluation;
    }
}
