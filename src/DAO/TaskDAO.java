package DAO;

import Model.Task;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

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
}
