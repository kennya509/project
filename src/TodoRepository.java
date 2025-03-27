import java.util.*;

public class TodoRepository {
    private final List<Task> tasks = new ArrayList<>();

    public TodoRepository() {
        tasks.add(new Task("Вивчити Java", "Прочитати підручник з Java"));
    }

    public void addTask(String title, String description) {
        Task newTask = new Task(title, description);
        tasks.add(newTask);
        System.out.println("Завдання успішно додано! ID: " + newTask.getId());
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public List<Task> searchTasks(String keyword, boolean completedStatus) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                if (task.isCompleted() == completedStatus) {
                    result.add(task);
                }
            }
        }
        return result;
    }
}