import java.io.Serializable;

// מודל של משימה
public class Task implements Serializable {
    private int id;               // מזהה ייחודי
    private String title;         // כותרת המשימה
    private String description;   // תיאור המשימה
    private Status status;        // סטטוס המשימה

    public Task(int id, String title, String description, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    // Getters ו-Setters מאפשרים גישה ושינוי ערכים
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        // מחרוזת שמציגה את כל פרטי המשימה
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
