import java.util.Scanner;

/**
 * Day 1 - Temperature Converter
 * Converts between Celsius and Fahrenheit.
 * Concepts: methods, if-else, taking input, formatted output.
 */
public class TemperatureConverter {

    static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Temperature Converter");
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Fahrenheit to Celsius");
        System.out.print("Choose 1 or 2: ");
        int choice = input.nextInt();

        System.out.print("Enter the temperature value: ");
        double value = input.nextDouble();

        if (choice == 1) {
            double result = celsiusToFahrenheit(value);
            System.out.printf("%.1f C = %.1f F%n", value, result);
        } else if (choice == 2) {
            double result = fahrenheitToCelsius(value);
            System.out.printf("%.1f F = %.1f C%n", value, result);
        } else {
            System.out.println("Invalid choice. Please run again and pick 1 or 2.");
        }

        input.close();
    }
}
