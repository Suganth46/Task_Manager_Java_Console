package Service;

import DAO.DBConnection;
import DAO.TaskDAO;
import Model.Note;
import Model.Task;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
//    private List<Task> taskList=new ArrayList<>();
    private TaskDAO taskDAO;
    public TaskService() {
        taskDAO = new TaskDAO();
    }
    private int taskCount=1;
    public void createTask(String title, String description, Date date) throws Exception {
        Task task=new Task(taskCount++,title,description,date);
        taskDAO.saveTask(task);
        System.out.println("Model.Task created successfully with ID: "+task.getTaskId());
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
    public void addNoteToTask(int id, String title, String body) {
        Task task=getTaskById(id);
        if(task!=null){
            int noteId=task.getNoteCount();
            Note note=new Note(noteId,title,body);
            task.addNote(note);
            task.setNoteCount(noteId++);
            System.out.println("Model.Note Added Successfully");
        }
        else{
            System.out.println("Model.Task Not Found");
        }
    }
    public boolean deleteNoteFromTask(int taskId, int noteId) {
        Task task=getTaskById(taskId);
        if(task!=null){
            return task.removeNote(noteId);
        }
        return false;
    }
    
}
