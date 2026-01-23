package Model;

public class Note {
    private int id;
    private int taskId;
    private String title;
    private String Body;
    public Note(int id, int taskId ,String title, String Body) {
        this.id=id;
        this.taskId=taskId;
        this.title=title;
        this.Body=Body;
    }

    //Getter
    public int getId() {
        return id;
    }
    public int getNoteTaskId() { return taskId;}
    public String getTitle(){ return title;}
    public String getBody(){ return Body;}

    @Override
    public String toString(){
        return String.format("%d. Title: %s \n Body: %s",id,title,Body);
    }
}