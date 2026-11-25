import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Day 13 - Shape Area Calculator
 *
 * An abstract Shape class defines what every shape must provide:
 * an area and a perimeter. Circle, Rectangle and Triangle each
 * extend Shape and implement those methods in their own way.
 *
 * The main program stores every shape in one ArrayList<Shape> and
 * calls area() / perimeter() polymorphically - Java picks the right
 * version at runtime depending on the actual object.
 */

abstract class Shape {
    private final String name;

    protected Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double area();

    public abstract double perimeter();

    public abstract String details();
}

class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String details() {
        return String.format("radius = %.2f", radius);
    }
}

class Rectangle extends Shape {
    private final double length;
    private final double width;

    public Rectangle(double length, double width) {
        super("Rectangle");
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }

    @Override
    public double perimeter() {
        return 2 * (length + width);
    }

    @Override
    public String details() {
        return String.format("length = %.2f, width = %.2f", length, width);
    }
}

class Triangle extends Shape {
    private final double sideA;
    private final double sideB;
    private final double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        super("Triangle");
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    public double area() {
        // Heron's formula: s = semi-perimeter
        double s = (sideA + sideB + sideC) / 2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }

    @Override
    public double perimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public String details() {
        return String.format("sides = %.2f, %.2f, %.2f", sideA, sideB, sideC);
    }
}

public class ShapeAreaCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Shape> shapes = new ArrayList<>();

        System.out.println("=== Shape Area Calculator ===");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt(scanner, "Choose an option: ");

            switch (choice) {
                case 1 -> {
                    double radius = readPositiveDouble(scanner, "Enter radius: ");
                    shapes.add(new Circle(radius));
                    System.out.println("Circle added.");
                }
                case 2 -> {
                    double length = readPositiveDouble(scanner, "Enter length: ");
                    double width = readPositiveDouble(scanner, "Enter width: ");
                    shapes.add(new Rectangle(length, width));
                    System.out.println("Rectangle added.");
                }
                case 3 -> {
                    double a = readPositiveDouble(scanner, "Enter side A: ");
                    double b = readPositiveDouble(scanner, "Enter side B: ");
                    double c = readPositiveDouble(scanner, "Enter side C: ");
                    if (isValidTriangle(a, b, c)) {
                        shapes.add(new Triangle(a, b, c));
                        System.out.println("Triangle added.");
                    } else {
                        System.out.println("Those sides cannot form a triangle. Nothing added.");
                    }
                }
                case 4 -> printAllShapes(shapes);
                case 5 -> printTotals(shapes);
                case 6 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid option. Please choose 1 to 6.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Add Circle");
        System.out.println("2. Add Rectangle");
        System.out.println("3. Add Triangle");
        System.out.println("4. Show all shapes");
        System.out.println("5. Show total area and perimeter");
        System.out.println("6. Exit");
    }

    private static void printAllShapes(ArrayList<Shape> shapes) {
        if (shapes.isEmpty()) {
            System.out.println("No shapes yet. Add one first.");
            return;
        }
        System.out.println("--- All shapes ---");
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            System.out.printf("%d. %s (%s) -> area = %.2f, perimeter = %.2f%n",
                    i + 1, shape.getName(), shape.details(), shape.area(), shape.perimeter());
        }
    }

    private static void printTotals(ArrayList<Shape> shapes) {
        if (shapes.isEmpty()) {
            System.out.println("No shapes yet. Add one first.");
            return;
        }
        double totalArea = 0;
        double totalPerimeter = 0;
        for (Shape shape : shapes) {
            totalArea += shape.area();
            totalPerimeter += shape.perimeter();
        }
        System.out.printf("Shapes: %d, total area = %.2f, total perimeter = %.2f%n",
                shapes.size(), totalArea, totalPerimeter);
    }

    private static boolean isValidTriangle(double a, double b, double c) {
        return a + b > c && a + c > b && b + c > a;
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a whole number.");
                scanner.next();
            }
        }
    }

    private static double readPositiveDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                if (value > 0) {
                    return value;
                }
                System.out.println("Value must be greater than 0.");
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number.");
                scanner.next();
            }
        }
    }
}
