package fudan.se.lab2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mail")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class MailBean implements Serializable {
    //private static final long serialVersionUID = -2116367492649751914L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    private String recipient;
    private String subject;
    @Column(name = "username")
    private String username;
    private int code;


  @Transient
    private String content;

    public MailBean(){

    }

    public MailBean(String recipient, String subject, String content){
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
