import java.util.Random;
import java.util.Scanner;

/**
 * Number Guessing Game.
 *
 * The computer picks a secret number between 1 and 100.
 * The player keeps guessing until the number is found.
 * After every guess the game says "too high" or "too low",
 * so each guess narrows the range. The game counts the
 * attempts and reports the score at the end.
 */
public class NumberGuessingGame {

    private static final int MIN = 1;
    private static final int MAX = 100;
    private static final int MAX_ATTEMPTS = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Optional: pass a secret number as an argument (e.g. "java NumberGuessingGame 42")
        // to play a fixed, reproducible game. Without an argument the number is random.
        int secret;
        if (args.length > 0) {
            secret = Integer.parseInt(args[0]);
            if (secret < MIN || secret > MAX) {
                System.out.println("Secret must be between " + MIN + " and " + MAX + ".");
                return;
            }
        } else {
            secret = random.nextInt(MAX - MIN + 1) + MIN;
        }
        int attempts = 0;
        boolean guessed = false;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have picked a number between " + MIN + " and " + MAX + ".");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts. Good luck!");

        while (attempts < MAX_ATTEMPTS && !guessed) {
            System.out.print("Attempt " + (attempts + 1) + "/" + MAX_ATTEMPTS
                    + " - enter your guess: ");

            if (!scanner.hasNext()) {
                System.out.println("\nNo more input. Goodbye!");
                break;
            }
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a whole number.");
                scanner.next(); // discard the invalid input
                continue;
            }

            int guess = scanner.nextInt();
            attempts++;

            if (guess < MIN || guess > MAX) {
                System.out.println("Out of range! Guess between " + MIN + " and " + MAX + ".");
            } else if (guess < secret) {
                System.out.println("Too low! Try a bigger number.");
            } else if (guess > secret) {
                System.out.println("Too high! Try a smaller number.");
            } else {
                guessed = true;
                System.out.println("Correct! You guessed it in " + attempts
                        + (attempts == 1 ? " attempt." : " attempts."));
            }
        }

        if (!guessed && attempts >= MAX_ATTEMPTS) {
            System.out.println("Out of attempts! The number was " + secret + ".");
        }

        scanner.close();
    }
}
