import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TaskManagerApp {
    private static TaskService service=new TaskService();
    private static Scanner scanner=new Scanner(System.in);
    private static DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static void main(String[] args) {
        System.out.println("Task Manager");
        while (true) {
            printMenu();
            System.out.print("Enter Choice: ");
            int choice=scanner.nextInt();
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
                    
                    break;
                case 7:
                    
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
    private static void deleteTask() {
        int id=getIntInput("Enter Task Id to Delete: ");
        if(service.deleteTask(id)){
            System.out.println("Task Delete");
        }
        else{
            System.out.println("Task Not Found");
        }
    }
    private static void setTaskComplete() {
            int id=getIntInput("Enter Task ID to mark complete: ");
            if(service.markTaskCompleted(id)){
                System.out.println("Task Mark As Completed");
            }
            else{
                System.out.println("Task Not Found");
            }
    }
    private static void getTaskById() {
        int id=getIntInput("Enter a Id: ");
        Task task=service.getTaskById(id);
    }
    private static int getIntInput(String string) {
        System.out.println(string);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid Input, Enter a Number");
            scanner.next();
        }
        int Number=scanner.nextInt();
        scanner.nextLine();
        return Number;
    }
    private static void createTask() {
        System.out.print("Enter Title: ");
        String title=scanner.nextLine();
        scanner.nextLine();
        System.out.print("Enter Description: ");
        String description=scanner.nextLine();
        LocalDate date=null;
        while(date==null){
            System.out.print("Enter a DeadLine (dd/MM/yyyy): ");
            String dateString=scanner.nextLine();
            try {
                date=LocalDate.parse(dateString, dateTimeFormatter);
            } catch (Exception e) {
                System.out.println("Invalid Date Format use dd/MM/yyyy");
            }
        }
        service.createTask(title,description,date);
    }
    private static void getAllTasks(){
        List<Task> task=service.getAllTasks();
        if(task.isEmpty()){
            System.out.println("No Task Available");
        }
        else{
            System.out.println("\n--- TASK LIST ---");

            for(Task t: task){
                System.out.println(t.toString());
                System.out.println("-----------------");
            }
        }
    }
}
