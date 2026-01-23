import Model.Note;
import Model.Task;
import Service.TaskService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TaskManagerApp {
    private static TaskService service=new TaskService();
    private static Scanner scanner=new Scanner(System.in);
    private static final DateTimeFormatter FORMATTER =DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static void main(String[] args) throws Exception {
        System.out.println("Task Manager");
        while (true) {
            printMenu();
            int choice=getIntInput("Enter choice: ");
            switch (choice) {
                case 1:
                    createTask();
                    break;
                case 2:
                    getAllTasks();
                    break;
                case 3:
                    getTaskById();
                    break;
                case 4:
                    setTaskComplete();
                    break;
                case 5:
                    deleteTask();
                    break;
                case 6:
                    addNoteToTask();
                    break;
                case 7:
                    deleteNoteFromTask();
                    break;
                case 0:
                    System.out.println("Exiting.......");
                    return;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }
    private static void printMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Create New Task");
        System.out.println("2. View All Tasks");
        System.out.println("3. View Task Details (with Notes)");
        System.out.println("4. Mark Task as Completed");
        System.out.println("5. Delete Task");
        System.out.println("6. Add Note to Task");
        System.out.println("7. Delete Note from Task");
        System.out.println("0. Exit");
    }
    private static void deleteNoteFromTask() throws Exception {
        int taskId=getIntInput("Enter a TaskId: ");
        int noteId=getIntInput("Enter a NoteId: ");
        if(service.deleteNoteFromTask(taskId,noteId)){
            System.out.println("Note Deleted");
        }
        else{
            System.out.println("Task or Note not found");
        }
    }
    private static void addNoteToTask() throws Exception {
        int id=getIntInput("Enter Task Id to Add Note: ");
        if(service.getTaskById(id)==null){
            System.out.println("Task Not Found");
            return;
        }
        System.out.print("Enter a Title: ");
        String title=scanner.nextLine();
        System.out.print("Enter a body: ");
        String body=scanner.nextLine();
        service.addNoteToTask(id,title,body);
    }
    private static void deleteTask() throws Exception {
        int id=getIntInput("Enter Task Id to Delete: ");
        if(service.deleteTask(id)){
            System.out.println("Task Delete");
        }
        else{
            System.out.println("Task Not Found");
        }
    }
    private static void setTaskComplete() throws Exception {
            int id=getIntInput("Enter Task ID to mark complete: ");
            if(service.markTaskCompleted(id)){
                System.out.println("Task Mark As Completed");
            }
            else{
                System.out.println("Task Not Found");
            }
    }
    private static void getTaskById() throws Exception {
        int id=getIntInput("Enter a Id: ");
        Task task=service.getTaskById(id);
        if(task!=null){
            System.out.println("\n=== TASK DETAILS ===");
            String status=task.getTaskComplete()?"[COMPLETED]":"[PENDING]";
            String title=(task.getTaskTitle()!=null && !task.getTaskTitle().isEmpty())?task.getTaskTitle():"No title";
            try {
                System.out.printf("%d. %s %s (Due: %s)\n Desc: %s\n",task.getTaskId(),status,title,task.getDeadline(),task.getTaskDescription());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("--- NOTES ---");
            List<Note> notes=service.getAllNotes(id);
            if(notes.isEmpty()){
                System.out.println("No notes attached.");
            }
            else{
                notes.forEach(System.out::println);
            }
        }
        else{
            System.out.println("Task Not Found");
        }
    }
    private static int getIntInput(String string) {
        System.out.print(string);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid Input, Enter a Number");
            scanner.next();
        }
        int Number=scanner.nextInt();
        scanner.nextLine();
        return Number;
    }
    private static void createTask() throws Exception {
        System.out.print("Enter Title: ");
        String title=scanner.nextLine();
        System.out.print("Enter Description: ");
        String description=scanner.nextLine();
        Date date=null;
        while(date==null){
            System.out.print("Enter a DeadLine (dd/MM/yyyy): ");
            String dateString=scanner.nextLine().trim();
            try {
                LocalDate temp=LocalDate.parse(dateString, FORMATTER);
                date=Date.valueOf(temp);
            } catch (Exception e) {
                System.out.println("Invalid Date Format use dd/MM/yyyy");
            }
        }
        service.createTask(title,description,date);
    }
    private static void getAllTasks() throws Exception{
        List<Task> task=service.getAllTasks();
        if(task.isEmpty()){
            System.out.println("No Task Available");
        }
        else{
            System.out.println("\n--- TASK LIST ---");

            for(Task t: task){
                String status=t.getTaskComplete()?"[COMPLETED]":"[PENDING]";
                String title=(t.getTaskTitle()!=null && !t.getTaskTitle().isEmpty())?t.getTaskTitle():"No title";
                try {
                    System.out.printf("%d. %s %s (Due: %s)\n Desc: %s\n notes count: %d\n",t.getTaskId(),status,title,t.getDeadline(),t.getTaskDescription(),service.getNotesCount(t.getTaskId()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.out.println("-----------------");
            }
        }
    }
}
