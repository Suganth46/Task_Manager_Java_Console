import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private int taskID;
    private String taskTitle;
    private String taskDescription;
    private LocalDate deadline;
    private List<Note> note;
    private boolean completed;
    
    public Task(int taskID,String taskTitle,String taskDescription,LocalDate deadline){
        this.taskID=taskID;
        this.taskTitle=taskTitle;
        this.taskDescription=taskDescription;
        this.deadline=deadline;
        this.note=new ArrayList<>();
        this.completed=false;
    }

    //Getter
    public int getTaskId(){
        return taskID;
    }
    public String getTaskTitle(){
        return taskTitle;
    }
    public String getTaskDescription(){
        return taskDescription;
    }

    @Override
    public String toString(){
        String status=completed?"[COMPLETED]":"[PENDING]";
        return String.format("%d. %s %s (Due: %s)\n Desc: %s\n notes count: %d",taskID,status,taskTitle,deadline,taskDescription,note.size());
    }
}