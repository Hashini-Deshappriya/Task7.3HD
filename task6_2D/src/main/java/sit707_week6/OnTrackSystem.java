package sit707_week6;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class OnTrackSystem {
    private Map<String, Task> taskMap = new HashMap<>();

    public Task submitTask(String studentId, String taskData) {
        String taskId = UUID.randomUUID().toString();
        Task task = new Task(taskId, studentId, taskData);
        taskMap.put(taskId, task);
        return task;
    }

    public List<Task> getTaskInbox(String studentId) {
        return taskMap.values().stream()
                .filter(task -> task.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    public Task viewTaskDetails(String taskId) {
        return taskMap.get(taskId);
    }

    public void sendMessage(String taskId, String senderId, String messageContent) {
        Task task = taskMap.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Invalid task ID.");
        }
        Message message = new Message(senderId, messageContent, LocalDateTime.now());
        task.getMessages().add(message);
    }

    public List<Message> getChatMessages(String taskId) {
        Task task = taskMap.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Invalid task ID.");
        }
        return task.getMessages();
    }

    public String getTaskStatus(String taskId) {
        Task task = taskMap.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Invalid task ID.");
        }
        return task.getStatus();
    }

    public void markTaskAsReviewed(String taskId) {
        Task task = taskMap.get(taskId);
        if (task != null) {
            task.setStatus("Reviewed");
        }
    }
}
