package fudan.se.lab2.controller.request;

public class ReplyRequest {
    private String  isbn;
    private String reply;
    private String userName;
    private int replyId;
    private int parentId;

    public ReplyRequest(){}

    public ReplyRequest(String isbn, String reply, String userName, int replyId, int parentId){
        this.isbn = isbn;
        this.parentId = parentId;
        this.reply = reply;
        this.userName = userName;
        this.replyId = replyId;
    }


    public String getUserName() {
        return userName;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getParentId() {
        return parentId;
    }

    public int getReplyId() {
        return replyId;
    }

    public String getReply() {
        return reply;
    }
}
