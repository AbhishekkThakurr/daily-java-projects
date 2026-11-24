# Day 12 — Tic-Tac-Toe Game

A two-player console Tic-Tac-Toe game. Players X and O take turns placing
their mark on a 3×3 board. Positions are numbered 1 to 9, like a phone keypad:

```
 1 | 2 | 3
---+---+---
 4 | 5 | 6
---+---+---
 7 | 8 | 9
```

The first player to get three marks in a row, column, or diagonal wins.
If the board fills up with no winner, the game is a draw.

## Concepts used

- **2D arrays** — the board is a `char[3][3]`; a position (1–9) maps to a
  row and column with `(position - 1) / 3` and `(position - 1) % 3`
- **Methods** — the game is split into small methods: `printBoard`,
  `readMove`, `placeMark`, `hasWon`, `isBoardFull`, `isFree`
- **while loop** — the main game loop runs until someone wins or the board is full
- **Input validation** — non-numbers, out-of-range positions, and already-taken
  cells are rejected and the player is asked again
- **Ternary operator** — switches the current player between X and O
- **Constants** — `SIZE`, `EMPTY`, `PLAYER_X`, `PLAYER_O` keep magic values out of the logic

## How to run

```bash
javac TicTacToe.java
java TicTacToe
```

## Sample run

Moves entered: `1`, `4`, `2`, `5`, `3` — X takes the whole top row and wins.

```
=== Tic-Tac-Toe ===
Player X goes first. Enter a position from 1 to 9.
   |   |
---+---+---
   |   |
---+---+---
   |   |
Enter position (1-9):  X |   |
---+---+---
   |   |
---+---+---
   |   |
Player O's turn.
Enter position (1-9):  X |   |
---+---+---
 O |   |
---+---+---
   |   |
Player X's turn.
Enter position (1-9):  X | X |
---+---+---
 O |   |
---+---+---
   |   |
Player O's turn.
Enter position (1-9):  X | X |
---+---+---
 O | O |
---+---+---
   |   |
Player X's turn.
Enter position (1-9):  X | X | X
---+---+---
 O | O |
---+---+---
   |   |
Player X wins!
```

Try it yourself: play a full game where nobody wins and see the
"It's a draw!" message, or type a letter instead of a number and see how
the game handles bad input.
