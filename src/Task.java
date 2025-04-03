public class Task {
    private static int counter = 1;
    private final int id;
    private final String title;
    private final String description;
    private final boolean completed;

    public Task(String title, String description, boolean completed) {
        this.id = counter++;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nЗавдання: " + title + "\nОпис: " + description + "\nВиконано: " + (completed ? "Так" : "Ні");
    }
}
