package sit707_week6;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private String id;
    private String studentId;
    private String data;
    private String status;
    private List<Message> messages;

    public Task(String id, String studentId, String data) {
        this.id = id;
        this.studentId = studentId;
        this.data = data;
        this.status = "Submitted";
        this.messages = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
