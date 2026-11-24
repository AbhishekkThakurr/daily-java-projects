import java.util.Scanner;

/**
 * Day 12 — Tic-Tac-Toe (2 players, console version).
 *
 * Two players take turns placing X and O on a 3x3 board.
 * Positions are numbered 1 to 9, like a phone keypad:
 *
 *   1 | 2 | 3
 *  ---+---+---
 *   4 | 5 | 6
 *  ---+---+---
 *   7 | 8 | 9
 *
 * The first player to get three marks in a row, column, or
 * diagonal wins. If the board fills up with no winner, it is a draw.
 */
public class TicTacToe {

    private static final int SIZE = 3;
    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[SIZE][SIZE];
        fillBoard(board);

        char currentPlayer = PLAYER_X;
        boolean gameOver = false;

        System.out.println("=== Tic-Tac-Toe ===");
        System.out.println("Player X goes first. Enter a position from 1 to 9.");
        printBoard(board);

        while (!gameOver) {
            int position = readMove(scanner, board);
            placeMark(board, position, currentPlayer);
            printBoard(board);

            if (hasWon(board, currentPlayer)) {
                System.out.println("Player " + currentPlayer + " wins!");
                gameOver = true;
            } else if (isBoardFull(board)) {
                System.out.println("It's a draw!");
                gameOver = true;
            } else {
                currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
                System.out.println("Player " + currentPlayer + "'s turn.");
            }
        }
        scanner.close();
    }

    /** Fill every cell with a blank space. */
    private static void fillBoard(char[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    /** Print the board with the grid lines. */
    private static void printBoard(char[][] board) {
        for (int row = 0; row < SIZE; row++) {
            System.out.println(" " + board[row][0] + " | " + board[row][1] + " | " + board[row][2]);
            if (row < SIZE - 1) {
                System.out.println("---+---+---");
            }
        }
    }

    /** Ask the current player for a move until a free, valid position is given. */
    private static int readMove(Scanner scanner, char[][] board) {
        while (true) {
            System.out.print("Enter position (1-9): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number from 1 to 9.");
                scanner.next(); // throw away the bad input
                continue;
            }
            int position = scanner.nextInt();
            if (position < 1 || position > 9) {
                System.out.println("Position must be between 1 and 9.");
            } else if (!isFree(board, position)) {
                System.out.println("That position is already taken. Try another one.");
            } else {
                return position;
            }
        }
    }

    /** Check whether a position (1-9) is still empty. */
    private static boolean isFree(char[][] board, int position) {
        int row = (position - 1) / SIZE;
        int col = (position - 1) % SIZE;
        return board[row][col] == EMPTY;
    }

    /** Place a mark on the board at the given position (1-9). */
    private static void placeMark(char[][] board, int position, char mark) {
        int row = (position - 1) / SIZE;
        int col = (position - 1) % SIZE;
        board[row][col] = mark;
    }

    /** Return true if the given player has three in a row. */
    private static boolean hasWon(char[][] board, char player) {
        // Check all rows and columns
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // a full row
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // a full column
            }
        }
        // Check both diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    /** Return true when no empty cell is left on the board. */
    private static boolean isBoardFull(char[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
