import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoRepository {
    private final List<Task> tasks = new ArrayList<>();
    private static final String FILE_NAME = "tasks.json";
    private final Gson gson = new Gson();

    public TodoRepository() {
        loadTasks();
        if (tasks.isEmpty()) {
            tasks.add(new Task("Вивчити Java", "Прочитати підручник з Java", false));
            saveTasks();
        }
    }

    public void addTask(String title, String description) {
        Task newTask = new Task(title, description, false);
        tasks.add(newTask);
        saveTasks(); // Сохраняем после добавления
        System.out.println("Завдання успішно додано! ID: " + newTask.getId());
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks); // Возвращаем копию списка
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void saveTasks() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            System.err.println("Помилка збереження файлу: " + e.getMessage());
        }
    }

    public void loadTasks() {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            List<Task> loadedTasks = gson.fromJson(reader, new TypeToken<List<Task>>() {}.getType());
            if (loadedTasks != null) {
                tasks.clear();
                tasks.addAll(loadedTasks);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено. Створюємо новий.");
        } catch (IOException e) {
            System.err.println("Помилка читання файлу: " + e.getMessage());
        }
    }

    public List<Task> searchTasks(String keyword, boolean completedStatus) {
        return tasks.stream()
                .filter(task -> task.getTitle().contains(keyword) || task.getDescription().contains(keyword))
                .filter(task -> task.isCompleted() == completedStatus)
                .collect(Collectors.toList());
    }
}
