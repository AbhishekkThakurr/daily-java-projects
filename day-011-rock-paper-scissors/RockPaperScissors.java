import java.util.Random;
import java.util.Scanner;

/**
 * Day 11 - Rock Paper Scissors Game
 *
 * A best-of-rounds game against the computer.
 * The player picks rock, paper or scissors, the computer
 * picks at random, and the first to win 3 rounds takes the match.
 */
public class RockPaperScissors {

    /** The three possible moves. An enum keeps the choices fixed and type-safe. */
    enum Choice {
        ROCK, PAPER, SCISSORS
    }

    private static final int ROUNDS_TO_WIN = 3;
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Rock Paper Scissors ===");
        System.out.println("First to win " + ROUNDS_TO_WIN + " rounds takes the match!");
        System.out.println("Type rock, paper or scissors (or 'quit' to stop).\n");

        int playerWins = 0;
        int computerWins = 0;
        int round = 1;

        while (playerWins < ROUNDS_TO_WIN && computerWins < ROUNDS_TO_WIN) {
            System.out.println("--- Round " + round + " ---");
            Choice playerChoice = readPlayerChoice(scanner);
            if (playerChoice == null) {
                System.out.println("Match abandoned. Thanks for playing!");
                scanner.close();
                return;
            }

            Choice computerChoice = randomChoice();
            System.out.println("You chose: " + playerChoice);
            System.out.println("Computer chose: " + computerChoice);

            int result = decideWinner(playerChoice, computerChoice);
            if (result > 0) {
                playerWins++;
                System.out.println("You win this round!");
            } else if (result < 0) {
                computerWins++;
                System.out.println("Computer wins this round!");
            } else {
                System.out.println("It's a tie! No points awarded.");
            }

            System.out.println("Score - You: " + playerWins + " | Computer: " + computerWins + "\n");
            round++;
        }

        if (playerWins == ROUNDS_TO_WIN) {
            System.out.println("You won the match " + playerWins + "-" + computerWins + ". Well played!");
        } else {
            System.out.println("Computer won the match " + computerWins + "-" + playerWins + ". Better luck next time!");
        }

        scanner.close();
    }

    /**
     * Reads the player's move, asking again until the input is valid.
     * Returns null if the player types "quit".
     */
    private static Choice readPlayerChoice(Scanner scanner) {
        while (true) {
            System.out.print("Your move (rock/paper/scissors): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("quit")) {
                return null;
            }

            switch (input) {
                case "rock":
                    return Choice.ROCK;
                case "paper":
                    return Choice.PAPER;
                case "scissors":
                    return Choice.SCISSORS;
                default:
                    System.out.println("Invalid move. Please type rock, paper or scissors.");
            }
        }
    }

    /** Picks a random move for the computer. */
    private static Choice randomChoice() {
        Choice[] values = Choice.values();
        return values[RANDOM.nextInt(values.length)];
    }

    /**
     * Decides the winner of one round.
     * Returns 1 if the player wins, -1 if the computer wins, 0 for a tie.
     *
     * The rule: rock beats scissors, scissors beats paper, paper beats rock.
     */
    private static int decideWinner(Choice player, Choice computer) {
        if (player == computer) {
            return 0;
        }

        boolean playerWins =
                (player == Choice.ROCK && computer == Choice.SCISSORS)
                || (player == Choice.SCISSORS && computer == Choice.PAPER)
                || (player == Choice.PAPER && computer == Choice.ROCK);

        return playerWins ? 1 : -1;
    }
}
