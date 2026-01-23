public class Note {
    private int id;
    private String title;
    private String Body;
    public Note(int id, String title, String Body) {
        this.id=id;
        this.title=title;
        this.Body=Body;
    }

    //Getter
    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        return String.format("%d. Title: %s \n Body: %s",id,title,Body);
    }
}