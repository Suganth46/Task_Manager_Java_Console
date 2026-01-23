package Model;

import java.sql.Date;

public class Task {
    private int taskID;
    private String taskTitle;
    private String taskDescription;
    private Date deadline;
    private boolean completed;
    public Task(int taskID,String taskTitle,String taskDescription,Date deadline){
        this.taskID=taskID;
        this.taskTitle=taskTitle;
        this.taskDescription=taskDescription;
        this.deadline=deadline;
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
    public Date getDeadline(){ return deadline;}
    public boolean getTaskComplete(){ return completed;}

    //Setter
    public void setTaskComplete(boolean completed){
        this.completed=completed;
    }

}