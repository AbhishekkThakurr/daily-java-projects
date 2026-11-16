import java.util.Scanner;

/**
 * Day 4 — Student Marks Analyzer
 *
 * Reads the marks of N subjects into an array, then reports the total,
 * average, highest and lowest marks, and the overall grade.
 */
public class StudentMarksAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many subjects? ");
        int n = scanner.nextInt();

        int[] marks = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + " (0-100): ");
            marks[i] = scanner.nextInt();
        }

        int total = calculateTotal(marks);
        double average = calculateAverage(marks);
        int highest = findHighest(marks);
        int lowest = findLowest(marks);
        String grade = calculateGrade(average);

        System.out.println();
        System.out.println("----- Result -----");
        System.out.println("Total marks: " + total + " / " + (n * 100));
        System.out.printf("Average: %.2f%%%n", average);
        System.out.println("Highest marks: " + highest);
        System.out.println("Lowest marks: " + lowest);
        System.out.println("Grade: " + grade);

        System.out.println();
        System.out.println("Subject-wise result:");
        for (int i = 0; i < n; i++) {
            String status = marks[i] >= 40 ? "Pass" : "Fail";
            System.out.println("Subject " + (i + 1) + ": " + marks[i] + " - " + status);
        }

        scanner.close();
    }

    // Adds up all the marks in the array.
    public static int calculateTotal(int[] marks) {
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        return sum;
    }

    // Average = total / number of subjects.
    public static double calculateAverage(int[] marks) {
        return (double) calculateTotal(marks) / marks.length;
    }

    // Scans the array once and keeps the biggest value seen.
    public static int findHighest(int[] marks) {
        int highest = marks[0];
        for (int mark : marks) {
            if (mark > highest) {
                highest = mark;
            }
        }
        return highest;
    }

    // Scans the array once and keeps the smallest value seen.
    public static int findLowest(int[] marks) {
        int lowest = marks[0];
        for (int mark : marks) {
            if (mark < lowest) {
                lowest = mark;
            }
        }
        return lowest;
    }

    // Grade bands based on the average percentage.
    public static String calculateGrade(double average) {
        if (average >= 90) {
            return "A+";
        } else if (average >= 80) {
            return "A";
        } else if (average >= 70) {
            return "B";
        } else if (average >= 60) {
            return "C";
        } else if (average >= 50) {
            return "D";
        } else if (average >= 40) {
            return "E";
        } else {
            return "F";
        }
    }
}
