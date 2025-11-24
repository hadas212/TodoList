import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Repository אחראי על שמירה וטעינה של המשימות מקובץ JSON
public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();
    private final String fileName = "tasks.json";

    public TaskRepository() {
        load(); // טעינת המשימות מקובץ בעת יצירת האובייקט
    }

    public void add(Task task) {
        tasks.add(task);
        save(); // שמירה אחרי הוספה
    }

    public void update(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == task.getId()) {
                tasks.set(i, task); // עדכון המשימה
                save();             // שמירה אחרי עדכון
                return;
            }
        }
    }

    public void delete(int id) {
        tasks.removeIf(t -> t.getId() == id); // מחיקת משימה לפי ID
        save(); // שמירה אחרי מחיקה
    }

    public Task getById(int id) {
        // מחזיר משימה לפי ID או null אם לא קיימת
        return tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public List<Task> listAll() {
        return tasks;
    }

    // שמירה ידנית לקובץ JSON
    private void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            pw.println("[");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                pw.println("  {");
                pw.println("    \"id\": " + t.getId() + ",");
                pw.println("    \"title\": \"" + t.getTitle() + "\",");
                pw.println("    \"description\": \"" + t.getDescription() + "\",");
                pw.println("    \"status\": \"" + t.getStatus() + "\"");
                pw.print("  }" + (i < tasks.size() - 1 ? "," : ""));
                pw.println();
            }
            pw.println("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // טעינה ידנית מקובץ JSON
    private void load() {
        tasks.clear(); // מנקה רשימת משימות קיימת
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            Task t = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("\"id\"")) {
                    int id = Integer.parseInt(line.split(":")[1].trim().replace(",", ""));
                    t = new Task(id, "", "", Status.NEW);
                } else if (line.startsWith("\"title\"") && t != null) {
                    String title = line.split(":")[1].trim().replace(",", "").replace("\"", "");
                    t.setTitle(title);
                } else if (line.startsWith("\"description\"") && t != null) {
                    String desc = line.split(":")[1].trim().replace(",", "").replace("\"", "");
                    t.setDescription(desc);
                } else if (line.startsWith("\"status\"") && t != null) {
                    String statusStr = line.split(":")[1].trim().replace("\"", "");
                    t.setStatus(Status.valueOf(statusStr));
                    tasks.add(t); // מוסיף את המשימה לרשימה
                }
            }
        } catch (IOException e) {
            System.out.println("No existing tasks found."); // במקרה שהקובץ לא קיים
        }
    }
}
