package fudan.se.lab2.Assist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class CommentRecord implements Serializable {
    private int bookId;
    private int commentId;
    private String isbn;
    private String cover;
    private String title;
    private String author;
    private String reverseTime;
    private String comment;
    private boolean isDelete;
    private boolean isRate;

    public CommentRecord(){}

    public CommentRecord(int bookId, String isbn, String title, String cover, String author, String reverseTime){
        this.author = author;
        this.cover = cover;
        this.title = title;
        this.isbn = isbn;
        this.reverseTime = reverseTime;
        this.bookId = bookId;//书的id
        //this.commentId = commentId;

    }

    public void setRate(boolean rate) {
        isRate = rate;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public boolean isRate() {
        return isRate;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getComment() {
        return comment;
    }

    public String getAuthor() {
        return author;
    }

    public int getCommentId() {
        return commentId;
    }

    public String getCover() {
        return cover;
    }

    public String getReverseTime() {
        return reverseTime;
    }

    public String getTitle() {
        return title;
    }

}
//id,isbn(是书不是副本),cover,title,author,borrowTime（最近借阅时间）,comment(书评，没有就空),isDelete(是否删除，bool值),isComment(是否评论，bool)
