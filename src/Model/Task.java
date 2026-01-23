package Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private int taskID;
    private String taskTitle;
    private String taskDescription;
    private Date deadline;
    private List<Note> note;
    private int noteCount;
    private boolean completed;
    
    public Task(int taskID,String taskTitle,String taskDescription,Date deadline){
        this.taskID=taskID;
        this.taskTitle=taskTitle;
        this.taskDescription=taskDescription;
        this.deadline=deadline;
        this.note=new ArrayList<>();
        this.completed=false;
        this.noteCount=1;
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
    public List<Note> getNotes() {
        return note;
    }
    public Date getDeadline(){ return deadline;}
    public boolean getTaskComplete(){ return completed;}
    public int getNoteCount() {
        return noteCount;
    }
    //Setter
    public void setTaskComplete(boolean b) {
        this.completed=true;
    }
    public void setNoteCount(int noteCount) {
        this.noteCount=noteCount;
    }
    
    public void addNote(Note note) {
        this.note.add(note);
    }
    
    public boolean removeNote(int noteId) {
        return note.removeIf(n-> n.getId()==noteId);
    }
    
    @Override
    public String toString(){
        String status=completed?"[COMPLETED]":"[PENDING]";
        String title=(taskTitle!=null && !taskTitle.isEmpty())?taskTitle:"No title";
        return String.format("%d. %s %s (Due: %s)\n Desc: %s\n notes count: %d",taskID,status,title,deadline,taskDescription,note.size());
    }

}