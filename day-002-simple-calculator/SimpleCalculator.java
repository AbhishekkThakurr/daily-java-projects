import java.util.Scanner;

/**
 * Day 2 — Simple Calculator
 *
 * A menu-driven calculator that performs basic arithmetic.
 * Concepts: methods, switch statement, do-while loop,
 * Scanner input, and handling divide-by-zero with an exception.
 */
public class SimpleCalculator {

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return a / b;
    }

    public static double power(double a, double b) {
        return Math.pow(a, b);
    }

    public static void printMenu() {
        System.out.println("\n--- Simple Calculator ---");
        System.out.println("1. Add (+)");
        System.out.println("2. Subtract (-)");
        System.out.println("3. Multiply (*)");
        System.out.println("4. Divide (/)");
        System.out.println("5. Power (^)");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            printMenu();

            while (!sc.hasNextInt()) {
                System.out.println("Please enter a number from the menu.");
                sc.next();
                printMenu();
            }
            choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("Goodbye!");
                break;
            }

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            System.out.print("Enter first number: ");
            double a = sc.nextDouble();
            System.out.print("Enter second number: ");
            double b = sc.nextDouble();

            switch (choice) {
                case 1:
                    System.out.printf("%.2f + %.2f = %.2f%n", a, b, add(a, b));
                    break;
                case 2:
                    System.out.printf("%.2f - %.2f = %.2f%n", a, b, subtract(a, b));
                    break;
                case 3:
                    System.out.printf("%.2f * %.2f = %.2f%n", a, b, multiply(a, b));
                    break;
                case 4:
                    try {
                        System.out.printf("%.2f / %.2f = %.2f%n", a, b, divide(a, b));
                    } catch (ArithmeticException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.printf("%.2f ^ %.2f = %.2f%n", a, b, power(a, b));
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }
}
