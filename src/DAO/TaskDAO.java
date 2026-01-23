package DAO;

import Model.Note;
import Model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    public void saveTask(Task task) throws Exception{
        String sql = """
            INSERT INTO tasks (task_id, task_title, task_description, deadline, completed)
            VALUES (?, ?, ?, ?, ?)
        """;

        try(Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);){

            ps.setInt(1, task.getTaskId());
            ps.setString(2, task.getTaskTitle());
            ps.setString(3, task.getTaskDescription());
            ps.setDate(4, task.getDeadline());
            ps.setBoolean(5,task.getTaskComplete());

            ps.executeUpdate();
        }
    }

    public List<Task> getAllTask() throws Exception{
        String tasksql= "SELECT * FROM tasks;";
        List<Task> tasks=new ArrayList<>();
        try(Connection con=DBConnection.getConnection();
        PreparedStatement tps=con.prepareStatement(tasksql);
        ResultSet taskrs=tps.executeQuery();){
            while (taskrs.next()){
                Task task=new Task(
                        taskrs.getInt("task_id"),
                        taskrs.getString("task_title"),
                        taskrs.getString("task_description"),
                        taskrs.getDate("deadline")
                );
                task.setTaskComplete(taskrs.getBoolean("completed"));
                tasks.add(task);
            }
        }
        return tasks;
    }

    public Task getTaskById(int id) throws Exception{
        String tasksql= "SELECT * FROM tasks WHERE task_id=?;";
        try(Connection con=DBConnection.getConnection();
        PreparedStatement tps=con.prepareStatement(tasksql);){
            tps.setInt(1, id);
            try(ResultSet taskrs=tps.executeQuery();){
                if(!taskrs.next()){
                    throw new Exception("No such task");
                }

                Task task=new Task(
                        taskrs.getInt("task_id"),
                        taskrs.getString("task_title"),
                        taskrs.getString("task_description"),
                        taskrs.getDate("deadline")
                );
                task.setTaskComplete(taskrs.getBoolean("completed"));
                return task;
            }
        }
    }

    public boolean deleteTask(int id) throws Exception{
        String tasksql= "DELETE FROM tasks WHERE task_id=?;";
        try(Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(tasksql);){
            ps.setInt(1, id);
            int rowAffected=ps.executeUpdate();
            return rowAffected > 0;
        }
    }

    public int getNotesCount(int taskID) throws Exception{
        String notesql= "SELECT COUNT(*) FROM notes WHERE task_id = ?";
        try(Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(notesql);){
            ps.setInt(1, taskID);
            try(ResultSet notes=ps.executeQuery();){
                if(notes.next()){
                    return notes.getInt(1);
                }
            }
        }
        return 0;
    }
    public int getTaskCount(){
        try {
            String tasksql = "SELECT COUNT(*) FROM tasks;";
            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(tasksql);) {
                try (ResultSet taskrs = ps.executeQuery();) {
                    if (taskrs.next()) {
                        return taskrs.getInt(1);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Note> getAllNotes(int id) throws Exception{
        String notesql= "SELECT * FROM notes WHERE task_id=?;";
        List<Note> notes=new ArrayList<>();
        try(Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(notesql);){
            ps.setInt(1, id);
            try(ResultSet noters=ps.executeQuery();) {
                while (noters.next()) {
                    Note note = new Note(
                            noters.getInt("note_id"),
                            noters.getInt("task_id"),
                            noters.getString("title"),
                            noters.getString("body")
                    );
                    notes.add(note);
                }
            }
        }
        return notes;
    }

    public boolean setTaskCompleted(int id) {
        String tasksql= "UPDATE tasks SET completed=true WHERE task_id=?;";
        try (Connection con=DBConnection.getConnection();
        PreparedStatement pst=con.prepareStatement(tasksql)){
            pst.setInt(1, id);
            int rowAffected=pst.executeUpdate();
            return rowAffected>0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
