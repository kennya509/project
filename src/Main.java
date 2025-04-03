import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TodoRepository todoRepo = new TodoRepository();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Програма запущена. У вас вже є збережені завдання.");

        boolean running = true;
        while (running) {
            System.out.println("\n=== Мій Менеджер Завдань ===");
            System.out.println("1. Додати нове завдання");
            System.out.println("2. Показати всі завдання");
            System.out.println("3. Пошук завдань");
            System.out.println("4. Вихід");
            System.out.print("Оберіть опцію (1-4): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Введіть назву завдання: ");
                    String title = scanner.nextLine();
                    System.out.print("Введіть опис завдання: ");
                    String description = scanner.nextLine();
                    todoRepo.addTask(title, description);
                    break;
                case "2":
                    List<Task> allTasks = todoRepo.getAllTasks();
                    if (allTasks.isEmpty()) {
                        System.out.println("Немає завдань для відображення.");
                    } else {
                        allTasks.forEach(task -> {
                            System.out.println(task);
                            System.out.println("--------------");
                        });
                    }
                    break;
                case "3":
                    System.out.print("Введіть ключове слово для пошуку: ");
                    String keyword = scanner.nextLine();
                    System.out.print("Шукати виконані завдання? (так/ні): ");
                    boolean completedStatus = scanner.nextLine().equalsIgnoreCase("так");
                    List<Task> searchResults = todoRepo.searchTasks(keyword, completedStatus);
                    if (searchResults.isEmpty()) {
                        System.out.println("Завдань за вашим запитом не знайдено.");
                    } else {
                        searchResults.forEach(task -> {
                            System.out.println(task);
                            System.out.println("--------------");
                        });
                    }
                    break;
                case "4":
                    running = false;
                    System.out.println("Кінець програми.");
                    break;
                default:
                    System.out.println("Невідома команда.");
            }
        }
    }
}
