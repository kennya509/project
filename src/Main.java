    import java.util.*;

    class TaskManager {
        static class Task {
            private String id;
            private String title;
            private String description;
            private boolean completed;

            public Task(String title, String description) {
                this.id = UUID.randomUUID().toString().substring(0, 4); // Коротший ID
                this.title = title;
                this.description = description;
                this.completed = false;
            }

            public String getId() { return id; }
            public String getTitle() { return title; }
            public void setTitle(String title) { this.title = title; }
            public String getDescription() { return description; }
            public void setDescription(String description) { this.description = description; }
            public boolean isCompleted() { return completed; }
            public void setCompleted(boolean completed) { this.completed = completed; }

            @Override
            public String toString() {
                return "Задача [" + id + "] - " + title + "\n" +
                        "  Опис: " + description + "\n" +
                        "  Статус: " + (completed ? "Виконано" : "Не виконано");
            }
        }

        private static ArrayList<Task> tasks = new ArrayList<>();
        private static Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            tasks.add(new Task("Вивчити Java", "Прочитати підручник з Java"));

            System.out.println("Програма запущена. У вас вже є 1 тестове завдання.");

            boolean running = true;
            while (running) {
                System.out.println("\n=== Мій Менеджер Завдань ===");
                System.out.println("1. Додати нове завдання");
                System.out.println("2. Показати всі завдання");
                System.out.println("3. Змінити завдання");
                System.out.println("4. Видалити завдання");
                System.out.println("5. Вихід");
                System.out.print("Оберіть опцію (1-5): ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        addTask();
                        break;
                    case "2":
                        showTasks();
                        break;
                    case "3":
                        editTask();
                        break;
                    case "4":
                        removeTask();
                        break;
                    case "5":
                        running = false;
                        System.out.println("Дякую за використання програми!");
                        break;
                    default:
                        System.out.println("Невідома команда, спробуйте ще раз");
                }
            }
        }

        private static void addTask() {
            System.out.println("\n-- Додавання нового завдання --");
            System.out.print("Введіть назву завдання: ");
            String title = scanner.nextLine();
            System.out.print("Введіть опис завдання: ");
            String description = scanner.nextLine();

            Task newTask = new Task(title, description);
            tasks.add(newTask);
            System.out.println("Завдання успішно додано! ID: " + newTask.getId());
        }

        private static void showTasks() {
            System.out.println("\n-- Список всіх завдань --");
            if (tasks.isEmpty()) {
                System.out.println("Немає завдань для відображення.");
                return;
            }

            sortTasks();

            for (Task task : tasks) {
                System.out.println(task);
                System.out.println("--------------");
            }
        }

        private static void sortTasks() {
            System.out.println("Оберіть спосіб сортування 1 - за назвою 2 - за статусом");
            String sortOption = scanner.nextLine();

            if (sortOption.equals("1")) {
                tasks.sort(Comparator.comparing(Task::getTitle));
            } else if (sortOption.equals("2")) {
                tasks.sort(Comparator.comparing(Task::isCompleted));
            } else {
                System.out.println("Невідомий вибір залишаємо без змін");
            }
        }

        private static void editTask() {
            System.out.print("Введіть ID завдання для редагування");
            String id = scanner.nextLine();

            for (Task task : tasks) {
                if (task.getId().equals(id)) {
                    System.out.print("Нова назва (Enter - не змінювати): ");
                    String newTitle = scanner.nextLine();
                    if (!newTitle.isEmpty()) task.setTitle(newTitle);

                    System.out.print("Новий опис (Enter - не змінювати): ");
                    String newDescription = scanner.nextLine();
                    if (!newDescription.isEmpty()) task.setDescription(newDescription);

                    System.out.print("Позначити як виконане? (так/ні): ");
                    String completed = scanner.nextLine();
                    if (completed.equalsIgnoreCase("так")) task.setCompleted(true);
                    else if (completed.equalsIgnoreCase("ні")) task.setCompleted(false);

                    System.out.println("Завдання оновлено!");
                    return;
                }
            }
            System.out.println("Завдання не знайдено.");
        }

        private static void removeTask() {
            System.out.print("Введіть ID завдання для видалення: ");
            String id = scanner.nextLine();

            for (Task task : tasks) {
                if (task.getId().equals(id)) {
                    tasks.remove(task);
                    System.out.println("Завдання видалено!");
                    return;
                }
            }
            System.out.println("Завдання не знайдено.");
        }
    }
