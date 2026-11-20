import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Day 8 - Contact Book.
 *
 * A small menu-driven contact book that stores contacts in a HashMap,
 * with the contact's name as the key and the phone number as the value.
 * Demonstrates HashMap: put, get, containsKey, remove, keySet and entrySet.
 */
public class ContactBook {

    private final Map<String, String> contacts = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ContactBook book = new ContactBook();
        book.seedSampleContacts();
        book.run();
    }

    /** Adds a few contacts so the book is not empty on first run. */
    private void seedSampleContacts() {
        contacts.put("Aarav", "98765 43210");
        contacts.put("Priya", "98123 45678");
        contacts.put("Rohan", "99988 77665");
    }

    private void run() {
        int choice;
        do {
            printMenu();
            choice = readInt("Choose an option (1-6): ");
            switch (choice) {
                case 1 -> addContact();
                case 2 -> findContact();
                case 3 -> updateContact();
                case 4 -> deleteContact();
                case 5 -> listContacts();
                case 6 -> System.out.println("Goodbye! Your contacts are saved in memory only.");
                default -> System.out.println("Invalid option. Please choose 1 to 6.");
            }
        } while (choice != 6);
    }

    private void printMenu() {
        System.out.println("\n===== Contact Book =====");
        System.out.println("1. Add contact");
        System.out.println("2. Find contact");
        System.out.println("3. Update contact");
        System.out.println("4. Delete contact");
        System.out.println("5. List all contacts");
        System.out.println("6. Exit");
    }

    private void addContact() {
        String name = readLine("Enter name: ");
        if (contacts.containsKey(name)) {
            System.out.println(name + " already exists. Use option 3 to update the number.");
            return;
        }
        String phone = readLine("Enter phone number: ");
        contacts.put(name, phone);
        System.out.println("Contact added: " + name + " -> " + phone);
    }

    private void findContact() {
        String name = readLine("Enter name to find: ");
        if (contacts.containsKey(name)) {
            System.out.println(name + " : " + contacts.get(name));
        } else {
            System.out.println("No contact found with the name " + name + ".");
        }
    }

    private void updateContact() {
        String name = readLine("Enter name to update: ");
        if (!contacts.containsKey(name)) {
            System.out.println("No contact found with the name " + name + ".");
            return;
        }
        String phone = readLine("Enter new phone number: ");
        contacts.put(name, phone);
        System.out.println("Updated: " + name + " -> " + phone);
    }

    private void deleteContact() {
        String name = readLine("Enter name to delete: ");
        String removed = contacts.remove(name);
        if (removed != null) {
            System.out.println("Deleted contact: " + name);
        } else {
            System.out.println("No contact found with the name " + name + ".");
        }
    }

    private void listContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts saved yet.");
            return;
        }
        System.out.println("\n--- All Contacts (" + contacts.size() + ") ---");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    /** Reads one full line of text, trimming surrounding spaces. */
    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /** Reads an integer, reprompting until the input is a valid number. */
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
