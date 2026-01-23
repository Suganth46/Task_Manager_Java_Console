package DAO;

import Model.Note;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class NoteDAO {
    public void saveNote(Note note) throws Exception{
        String sql = """
            INSERT INTO notes (note_id, task_id, title, body)
            VALUES (?, ?, ?, ?)
        """;
        try(Connection con= DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement(sql);){
            pst.setInt(1,note.getId());
            pst.setInt(2,note.getNoteTaskId());
            pst.setString(3,note.getTitle());
            pst.setString(4,note.getBody());
            pst.executeUpdate();
        }
    }

    public boolean removeNoteById(int noteId, int taskId) throws  Exception{
        String sql = """
            DELETE  FROM notes WHERE note_id = ? AND task_id = ?;
        """;
        try(Connection connection=DBConnection.getConnection();
            PreparedStatement pst=connection.prepareStatement(sql);){
            pst.setInt(1,noteId);
            pst.setInt(2,taskId);
            int rowsAffected =pst.executeUpdate();
            return rowsAffected >0;
        }
    }
}
