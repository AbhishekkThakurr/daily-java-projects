import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Todo List with File Saving.
 *
 * A small to-do list that remembers your tasks. Tasks are saved to a text
 * file (tasks.txt) every time they change, and loaded back automatically
 * the next time the program starts — so nothing is lost when you exit.
 */
public class TodoList {

    private static final String FILE_NAME = "tasks.txt";

    // One task: its text and whether it is done.
    static class Task {
        String text;
        boolean done;

        Task(String text, boolean done) {
            this.text = text;
            this.done = done;
        }
    }

    private final ArrayList<Task> tasks = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TodoList app = new TodoList();
        app.loadTasks();
        app.run();
    }

    private void run() {
        System.out.println("=== Todo List ===");
        System.out.println(tasks.size() + " task(s) loaded from " + FILE_NAME);

        while (true) {
            showMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addTask();
                case "2" -> listTasks();
                case "3" -> markDone();
                case "4" -> deleteTask();
                case "5" -> {
                    saveTasks();
                    System.out.println("Tasks saved. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please choose 1-5.");
            }
        }
    }

    private void showMenu() {
        System.out.println();
        System.out.println("1. Add a task");
        System.out.println("2. List all tasks");
        System.out.println("3. Mark a task as done");
        System.out.println("4. Delete a task");
        System.out.println("5. Save and exit");
    }

    private void addTask() {
        System.out.print("Enter the task: ");
        String text = scanner.nextLine().trim();
        if (text.isEmpty()) {
            System.out.println("Task cannot be empty.");
            return;
        }
        tasks.add(new Task(text, false));
        saveTasks();
        System.out.println("Task added.");
    }

    private void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet. Add one first!");
            return;
        }
        System.out.println();
        System.out.println("Your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String mark = task.done ? "[x]" : "[ ]";
            System.out.println((i + 1) + ". " + mark + " " + task.text);
        }
    }

    private void markDone() {
        listTasks();
        if (tasks.isEmpty()) {
            return;
        }
        System.out.print("Enter the task number to mark as done: ");
        int number = readNumber();
        if (number < 1 || number > tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }
        tasks.get(number - 1).done = true;
        saveTasks();
        System.out.println("Task marked as done.");
    }

    private void deleteTask() {
        listTasks();
        if (tasks.isEmpty()) {
            return;
        }
        System.out.print("Enter the task number to delete: ");
        int number = readNumber();
        if (number < 1 || number > tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }
        Task removed = tasks.remove(number - 1);
        saveTasks();
        System.out.println("Deleted: " + removed.text);
    }

    private int readNumber() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Reads tasks back from the file. Each line is stored as:
    //   true|Buy groceries   or   false|Finish Java homework
    private void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 2);
                if (parts.length == 2) {
                    boolean done = parts[0].equals("true");
                    tasks.add(new Task(parts[1], done));
                }
            }
        } catch (IOException e) {
            // No saved file yet (first run) — start with an empty list.
            System.out.println("No saved tasks found. Starting fresh.");
        }
    }

    // Writes every task to the file, one per line.
    private void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.done + "|" + task.text);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not save tasks: " + e.getMessage());
        }
    }
}
