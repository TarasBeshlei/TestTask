package ua.besh.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String messageBody;

    private String messageAuthor;

    public Task() {

    }

    public Task(String messageBody, String messageAuthor) {
        this.messageAuthor = messageAuthor;
        this.messageBody = messageBody;
    }


    public String getMessageAuthor() {
        return messageAuthor;
    }

    public void setMessageAuthor(String messageAuthor) {
        this.messageAuthor = messageAuthor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
