import java.util.ArrayList;
import java.util.Scanner;

/**
 * Day 15 — Movie Ticket Booking
 *
 * A menu-driven console program that books seats in a small cinema
 * hall (5 rows x 8 seats). Seat price depends on the row: rows 1-2
 * are Regular, rows 3-4 are Premium and row 5 is Recliner. Booking
 * a seat that is already taken throws a custom exception, which the
 * program catches and reports without crashing.
 */
enum SeatType {
    REGULAR(200),
    PREMIUM(350),
    RECLINER(500);

    private final double price;

    SeatType(double price) {
        this.price = price;
    }

    double getPrice() {
        return price;
    }

    static SeatType forRow(int row) {
        if (row <= 2) {
            return REGULAR;
        } else if (row <= 4) {
            return PREMIUM;
        } else {
            return RECLINER;
        }
    }
}

class SeatNotAvailableException extends Exception {
    SeatNotAvailableException(String message) {
        super(message);
    }
}

class Booking {
    private final String customerName;
    private final int row;
    private final int column;
    private final SeatType seatType;

    Booking(String customerName, int row, int column, SeatType seatType) {
        this.customerName = customerName;
        this.row = row;
        this.column = column;
        this.seatType = seatType;
    }

    int getRow() {
        return row;
    }

    int getColumn() {
        return column;
    }

    double getPrice() {
        return seatType.getPrice();
    }

    @Override
    public String toString() {
        return String.format("%s | Row %d, Seat %d | %s | %.2f",
                customerName, row, column, seatType, getPrice());
    }
}

public class MovieTicketBooking {
    private static final int ROWS = 5;
    private static final int COLUMNS = 8;

    private static final Scanner scanner = new Scanner(System.in);
    private static final boolean[][] booked = new boolean[ROWS][COLUMNS];
    private static final ArrayList<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Movie Ticket Booking ===");
        System.out.println("Hall: " + ROWS + " rows x " + COLUMNS + " seats");
        System.out.println("Rows 1-2: Regular (200.00), Rows 3-4: Premium (350.00), Row 5: Recliner (500.00)");

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("1. Book a seat");
            System.out.println("2. Cancel a booking");
            System.out.println("3. Show seat map");
            System.out.println("4. Show all bookings");
            System.out.println("5. Show total revenue");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> handleBooking();
                case 2 -> handleCancellation();
                case 3 -> showSeatMap();
                case 4 -> showBookings();
                case 5 -> showRevenue();
                case 6 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please choose 1-6.");
            }
        }
        scanner.close();
    }

    private static void handleBooking() {
        System.out.print("Enter customer name: ");
        String name = readName();
        System.out.print("Enter row (1-" + ROWS + "): ");
        int row = readIntInRange(1, ROWS);
        System.out.print("Enter seat number (1-" + COLUMNS + "): ");
        int column = readIntInRange(1, COLUMNS);

        try {
            Booking booking = bookSeat(name, row, column);
            System.out.println("Booked: " + booking);
        } catch (SeatNotAvailableException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    private static Booking bookSeat(String name, int row, int column)
            throws SeatNotAvailableException {
        if (booked[row - 1][column - 1]) {
            throw new SeatNotAvailableException(
                    "Row " + row + ", Seat " + column + " is already booked.");
        }
        booked[row - 1][column - 1] = true;
        Booking booking = new Booking(name, row, column, SeatType.forRow(row));
        bookings.add(booking);
        return booking;
    }

    private static void handleCancellation() {
        System.out.print("Enter row (1-" + ROWS + "): ");
        int row = readIntInRange(1, ROWS);
        System.out.print("Enter seat number (1-" + COLUMNS + "): ");
        int column = readIntInRange(1, COLUMNS);

        if (!booked[row - 1][column - 1]) {
            System.out.println("That seat is not booked, so there is nothing to cancel.");
            return;
        }
        booked[row - 1][column - 1] = false;
        bookings.removeIf(b -> b.getRow() == row && b.getColumn() == column);
        System.out.println("Cancelled booking for Row " + row + ", Seat " + column + ".");
    }

    private static void showSeatMap() {
        System.out.println("--- Seat map (. = free, X = booked) ---");
        System.out.print("     ");
        for (int c = 1; c <= COLUMNS; c++) {
            System.out.print(c + " ");
        }
        System.out.println();
        for (int r = 0; r < ROWS; r++) {
            System.out.print("R" + (r + 1) + "   ");
            for (int c = 0; c < COLUMNS; c++) {
                System.out.print((booked[r][c] ? "X" : ".") + " ");
            }
            System.out.println(" (" + SeatType.forRow(r + 1) + ")");
        }
    }

    private static void showBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }
        System.out.println("--- All bookings ---");
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println((i + 1) + ". " + bookings.get(i));
        }
    }

    private static void showRevenue() {
        double total = 0;
        for (Booking booking : bookings) {
            total += booking.getPrice();
        }
        System.out.printf("Total revenue: %.2f%n", total);
    }

    private static String readName() {
        while (true) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.print("Name cannot be empty. Enter customer name: ");
        }
    }

    private static int readInt() {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a whole number: ");
            }
        }
    }

    private static int readIntInRange(int min, int max) {
        while (true) {
            int value = readInt();
            if (value >= min && value <= max) {
                return value;
            }
            System.out.print("Please enter a number between " + min + " and " + max + ": ");
        }
    }
}
