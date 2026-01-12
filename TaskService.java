import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private List<Task> taskList=new ArrayList<>();
    private int taskCount=1;
    public void createTask(String title, String description, LocalDate date) {
        Task task=new Task(taskCount++,title,description,date);
        taskList.add(task);
        System.out.println("Task created successfully with ID: "+task.getTaskId());
    }
    public List<Task> getAllTasks() {
        return taskList;
    }
    public Task getTaskById(int id) {
        return taskList.stream()
        .filter(t-> t.getTaskId()==id)
        .findFirst()
        .orElse(null);
    }
    public boolean markTaskCompleted(int id) {
        Task task=getTaskById(id);
        if(task!=null){
            task.setTaskComplete(true);
            return true;
        }
        return false;
    }
    public boolean deleteTask(int id) {
        return taskList.removeIf(t-> t.getTaskId()==id);
    }
    
}
