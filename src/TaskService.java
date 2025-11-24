import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {
    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void markAsDone(int id) {
        Task task = repository.getById(id);
        if (task != null) {
            task.setStatus(Status.DONE);
            repository.update(task);
        }
    }

    public List<Task> search(String keyword) {
        return repository.listAll().stream()
                .filter(t -> t.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        t.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Task> listSortedByStatus() {
        return repository.listAll().stream()
                .sorted(Comparator.comparing(Task::getStatus))
                .collect(Collectors.toList());
    }
}
