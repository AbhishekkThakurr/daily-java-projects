import java.util.ArrayList;
import java.util.Scanner;

/**
 * Day 10 — Quiz Game
 *
 * A multiple-choice quiz game. The program asks 5 Java questions,
 * checks each answer, keeps the score, and shows a result message
 * at the end.
 *
 * Concepts used: classes and objects, ArrayList, Scanner input,
 * for-each loop, if-else, methods, input validation.
 */
public class QuizGame {

    /**
     * One quiz question. It stores the question text, four options
     * and the correct option (1-4).
     */
    static class Question {
        private String text;
        private String[] options;
        private int correctOption; // 1 to 4

        public Question(String text, String option1, String option2,
                        String option3, String option4, int correctOption) {
            this.text = text;
            this.options = new String[]{option1, option2, option3, option4};
            this.correctOption = correctOption;
        }

        /** Shows the question and its four options. */
        public void display(int number) {
            System.out.println("\nQ" + number + ". " + text);
            for (int i = 0; i < options.length; i++) {
                System.out.println("   " + (i + 1) + ") " + options[i]);
            }
        }

        /** Returns true if the given answer (1-4) is correct. */
        public boolean isCorrect(int answer) {
            return answer == correctOption;
        }

        public int getCorrectOption() {
            return correctOption;
        }

        public String getCorrectText() {
            return options[correctOption - 1];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Question> questions = buildQuestions();

        System.out.println("==============================");
        System.out.println("   Welcome to the Quiz Game!");
        System.out.println("==============================");
        System.out.println("Answer each question by typing 1, 2, 3 or 4.");
        System.out.println("There are " + questions.size() + " questions. Good luck!");

        int score = 0;
        int questionNumber = 1;

        for (Question question : questions) {
            question.display(questionNumber);
            int answer = readAnswer(scanner);

            if (question.isCorrect(answer)) {
                System.out.println("Correct! Well done.");
                score++;
            } else {
                System.out.println("Wrong. The correct answer is "
                        + question.getCorrectOption() + ") "
                        + question.getCorrectText() + ".");
            }
            questionNumber++;
        }

        showResult(score, questions.size());
        scanner.close();
    }

    /** Builds the list of quiz questions. */
    private static ArrayList<Question> buildQuestions() {
        ArrayList<Question> questions = new ArrayList<>();

        questions.add(new Question(
                "Which keyword is used to create an object in Java?",
                "class", "new", "this", "object", 2));

        questions.add(new Question(
                "What is the size of an int in Java?",
                "2 bytes", "4 bytes", "8 bytes", "16 bytes", 2));

        questions.add(new Question(
                "Which loop checks its condition AFTER running the body once?",
                "for loop", "while loop", "do-while loop", "for-each loop", 3));

        questions.add(new Question(
                "Which of these is used to store a list of items that can grow?",
                "Array", "ArrayList", "String", "int", 2));

        questions.add(new Question(
                "What does JVM stand for?",
                "Java Virtual Machine", "Java Variable Method",
                "Java Verified Module", "Joint Virtual Machine", 1));

        return questions;
    }

    /**
     * Reads the user's answer. Keeps asking until a number from 1 to 4
     * is entered, so a typo never crashes the game.
     */
    private static int readAnswer(Scanner scanner) {
        while (true) {
            System.out.print("Your answer (1-4): ");
            String input = scanner.nextLine().trim();
            try {
                int answer = Integer.parseInt(input);
                if (answer >= 1 && answer <= 4) {
                    return answer;
                }
                System.out.println("Please enter a number between 1 and 4.");
            } catch (NumberFormatException e) {
                System.out.println("That is not a number. Please enter 1, 2, 3 or 4.");
            }
        }
    }

    /** Prints the final score and a message based on how well the player did. */
    private static void showResult(int score, int total) {
        System.out.println("\n==============================");
        System.out.println("   Quiz finished!");
        System.out.println("   Your score: " + score + " / " + total);
        System.out.println("==============================");

        if (score == total) {
            System.out.println("Perfect score! You are a Java star.");
        } else if (score >= total * 3 / 4) {
            System.out.println("Great job! Almost perfect.");
        } else if (score >= total / 2) {
            System.out.println("Good effort! Keep practising.");
        } else {
            System.out.println("Keep learning — try the quiz again tomorrow!");
        }
    }
}
