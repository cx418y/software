package fudan.se.lab2.Assist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fudan.se.lab2.domain.Reply;

import java.io.Serializable;
import java.util.List;
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class ComRepRocord implements Serializable {
    private int commentId;
    private String userName;
    private double rate;
    private String time;
    private String content;
    private List<Reply> replyList;

    public ComRepRocord(){}

    public ComRepRocord(int commentId, String userName, double rate, String time, List<Reply> replyList){
        this.commentId = commentId;
        //this.content = content;
        this.rate = rate;
        this.replyList = replyList;
        this.userName = userName;
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public double getRate() {
        return rate;
    }

    public int getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public String getTime() {
        return time;
    }
}
