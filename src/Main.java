import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // יצירת Repository ו-Service לניהול המשימות
        TaskRepository repository = new TaskRepository();
        TaskService service = new TaskService(repository);

        // Scanner לקריאת קלט מהמשתמש
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // תפריט בסיסי
            System.out.println("\n--- Task Manager ---");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Done");
            System.out.println("3. Delete Task");
            System.out.println("4. List All Tasks");
            System.out.println("5. List Tasks Sorted by Status");
            System.out.println("6. Search Tasks");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // מנקה את השורה כדי שהקלט הבא יהיה תקין

            switch (option) {
                case 1:
                    // הוספת משימה חדשה
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine();
                    // יצירת מזהה ייחודי רציף
                    int id = repository.listAll().size() + 1;
                    Task newTask = new Task(id, title, desc, Status.NEW);
                    repository.add(newTask);
                    System.out.println("Task added!");
                    break;

                case 2:
                    // סימון משימה כ-DONE
                    System.out.print("Enter Task ID to mark as done: ");
                    int doneId = scanner.nextInt();
                    service.markAsDone(doneId);
                    System.out.println("Task updated!");
                    break;

                case 3:
                    // מחיקת משימה לפי ID
                    System.out.print("Enter Task ID to delete: ");
                    int deleteId = scanner.nextInt();
                    repository.delete(deleteId);
                    System.out.println("Task deleted!");
                    break;

                case 4:
                    // הצגת כל המשימות
                    System.out.println("\nAll Tasks:");
                    for (Task t : repository.listAll()) {
                        System.out.println(t);
                    }
                    break;

                case 5:
                    // הצגת משימות ממויינות לפי סטטוס
                    System.out.println("\nTasks Sorted by Status:");
                    List<Task> sortedTasks = service.listSortedByStatus();
                    for (Task t : sortedTasks) {
                        System.out.println(t);
                    }
                    break;

                case 6:
                    // חיפוש משימות לפי מילת מפתח
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.next();
                    List<Task> results = service.search(keyword);
                    System.out.println("\nSearch Results:");
                    for (Task t : results) {
                        System.out.println(t);
                    }
                    break;

                case 0:
                    // יציאה מהתוכנית
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}
