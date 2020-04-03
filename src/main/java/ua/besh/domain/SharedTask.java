package ua.besh.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SharedTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long taskId;
    private String cooperatorEmail;

    public SharedTask() {
    }

    public SharedTask(Long taskId, String cooperatorEmail) {
        this.taskId = taskId;
        this.cooperatorEmail = cooperatorEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getCooperatorEmail() {
        return cooperatorEmail;
    }

    public void setCooperatorEmail(String cooperatorEmail) {
        this.cooperatorEmail = cooperatorEmail;
    }
}
