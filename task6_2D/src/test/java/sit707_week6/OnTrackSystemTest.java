package sit707_week6;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;



public class OnTrackSystemTest {
    private OnTrackSystem system;

    @Before
    public void setUp() {
        system = new OnTrackSystem();
    }

    // R - Right result
    @Test
    public void testSubmitTask_Right() {
        Task task = system.submitTask("student1", "My assignment data");
        assertEquals("student1", task.getStudentId());
        assertEquals("Submitted", task.getStatus());
    }

    // B - Boundary conditions
    @Test
    public void testInboxBoundary() {
        List<Task> inbox = system.getTaskInbox("nonexistent");
        assertTrue(inbox.isEmpty());
    }

    // I - Inverse relationships
    @Test
    public void testSendMessage_Inverse() {
        Task task = system.submitTask("student2", "Another task");
        assertEquals(0, task.getMessages().size());
        system.sendMessage(task.getId(), "student2", "Hi Tutor");
        assertEquals(1, system.getChatMessages(task.getId()).size());
    }

    // C - Cross-check with alternate path
    @Test
    public void testViewTaskDetails_CrossCheck() {
        Task task = system.submitTask("student3", "Task data");
        Task fetchedTask = system.viewTaskDetails(task.getId());
        assertEquals(task.getId(), fetchedTask.getId());
    }

    // E - Error conditions
    @Test
    public void testSendMessage_Error() {
        assertThrows(IllegalArgumentException.class, () -> {
            system.sendMessage("invalidId", "student", "Test message");
        });
    }

    // P - Performance
    @Test
    public void testBulkSubmission_Performance() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            system.submitTask("bulkUser", "Task " + i);
        }
        long duration = System.currentTimeMillis() - start;
        assertTrue(duration < 1000); // Acceptable performance under 1 second
    }
}
