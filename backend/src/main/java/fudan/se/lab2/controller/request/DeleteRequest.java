package fudan.se.lab2.controller.request;

public class DeleteRequest {
    //private String isbn;
    private String userName;
    private int commentId;
    //private String deleteName;

    public DeleteRequest(){}

    public DeleteRequest(String userName, int commentId){
       // this.isbn = isbn;
        this.commentId = commentId;
        this.userName = userName;
    }

    //public String getIsbn() {
      //  return isbn;
   // }

    public int getCommentId() {
        return commentId;
    }

    public String getUserName() {
        return userName;
    }



    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
