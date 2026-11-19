import java.util.ArrayList;
import java.util.Scanner;

/**
 * Library Book Manager — Day 7 of the Daily Java Projects.
 *
 * A tiny menu-driven library: add books, list them, search by title,
 * and borrow / return a book. Demonstrates classes, objects and
 * ArrayList (a collection that grows and shrinks as you add books).
 */
public class LibraryBookManager {

    /** One book in the library. */
    static class Book {
        String title;
        String author;
        boolean borrowed;

        Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.borrowed = false;
        }

        void borrow() {
            borrowed = true;
        }

        void giveBack() {
            borrowed = false;
        }

        String status() {
            return borrowed ? "Borrowed" : "Available";
        }

        @Override
        public String toString() {
            return "\"" + title + "\" by " + author + " — " + status();
        }
    }

    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        // A couple of books to start with, so the list is never empty.
        books.add(new Book("The Alchemist", "Paulo Coelho"));
        books.add(new Book("Clean Code", "Robert C. Martin"));

        while (true) {
            System.out.println("\n--- Library Book Manager ---");
            System.out.println("1. Add a book");
            System.out.println("2. List all books");
            System.out.println("3. Search by title");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume the leftover newline

            if (choice == 6) {
                System.out.println("Goodbye! Total books in library: " + books.size());
                break;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Book title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    books.add(new Book(title, author));
                    System.out.println("Added: \"" + title + "\" by " + author);
                }
                case 2 -> {
                    if (books.isEmpty()) {
                        System.out.println("No books in the library yet.");
                    } else {
                        System.out.println("Books in the library:");
                        for (int i = 0; i < books.size(); i++) {
                            System.out.println((i + 1) + ". " + books.get(i));
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Title to search: ");
                    String query = sc.nextLine();
                    boolean found = false;
                    for (Book book : books) {
                        if (book.title.equalsIgnoreCase(query)) {
                            System.out.println("Found: " + book);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("No book titled \"" + query + "\" found.");
                    }
                }
                case 4 -> {
                    System.out.print("Title to borrow: ");
                    String title = sc.nextLine();
                    Book book = findByTitle(books, title);
                    if (book == null) {
                        System.out.println("No such book in the library.");
                    } else if (book.borrowed) {
                        System.out.println("\"" + book.title + "\" is already borrowed.");
                    } else {
                        book.borrow();
                        System.out.println("You borrowed \"" + book.title + "\". Enjoy reading!");
                    }
                }
                case 5 -> {
                    System.out.print("Title to return: ");
                    String title = sc.nextLine();
                    Book book = findByTitle(books, title);
                    if (book == null) {
                        System.out.println("No such book in the library.");
                    } else if (!book.borrowed) {
                        System.out.println("\"" + book.title + "\" was not borrowed.");
                    } else {
                        book.giveBack();
                        System.out.println("Thank you for returning \"" + book.title + "\".");
                    }
                }
                default -> System.out.println("Invalid option. Please choose 1-6.");
            }
        }
        sc.close();
    }

    /** Returns the first book whose title matches (case-insensitive), or null. */
    static Book findByTitle(ArrayList<Book> books, String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}
