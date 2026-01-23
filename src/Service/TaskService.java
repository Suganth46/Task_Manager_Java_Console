package Service;

import DAO.NoteDAO;
import DAO.TaskDAO;
import Model.Note;
import Model.Task;

import java.sql.Date;
import java.util.List;

public class TaskService {
    private TaskDAO taskDAO;
    private NoteDAO noteDAO;
    public TaskService() {
        noteDAO = new NoteDAO();
        taskDAO = new TaskDAO();
    }
    public void createTask(String title, String description, Date date) throws Exception {
        int taskCount=taskDAO.getTaskCount();
        Task task=new Task(taskCount++,title,description,date);
        taskDAO.saveTask(task);
        System.out.println("Task created successfully with ID: "+task.getTaskId());
    }
    public List<Task> getAllTasks() throws Exception {
        return taskDAO.getAllTask();
    }
    public Task getTaskById(int id) throws Exception {
        return taskDAO.getTaskById(id);
    }
    public boolean markTaskCompleted(int id) throws Exception {
        Task task=getTaskById(id);
        if(task!=null){
           return taskDAO.setTaskCompleted(id);
        }
        return false;
    }
    public boolean deleteTask(int id) throws Exception {
        return taskDAO.deleteTask(id);
    }
    public void addNoteToTask(int id, String title, String body) throws Exception {
        Task task=getTaskById(id);
        if(task!=null){
            int noteId=taskDAO.getNotesCount(id);
            Note note=new Note(noteId+1,id,title,body);
            noteDAO.saveNote(note);
            System.out.println("Note Added Successfully");
        }
        else{
            System.out.println("Task Not Found");
        }
    }
    public boolean deleteNoteFromTask(int taskId, int noteId) throws Exception {
        Task task=getTaskById(taskId);
        if(task!=null){
            return noteDAO.removeNoteById(noteId,taskId);
        }
        return false;
    }
    public List<Note> getAllNotes(int id) throws Exception {
        return taskDAO.getAllNotes(id);
    }
    public int getNotesCount(int taskId) throws Exception {
        return taskDAO.getNotesCount(taskId);
    }
}
