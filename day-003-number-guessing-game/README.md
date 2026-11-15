# Day 3 — Number Guessing Game

A small console game: the computer picks a secret number between 1 and 100,
and you guess it. After each guess the game tells you "too high" or "too low",
so every guess narrows the range. You have 10 attempts — guess it in as few
tries as you can.

## Concepts used

- `while` loop with two exit conditions (attempts left and not yet guessed)
- `if / else if / else` chains for the too-high / too-low / correct logic
- `java.util.Random` to pick the secret number
- `java.util.Scanner` for keyboard input, with a guard against non-number input
- Constants (`final`) for the range and the attempt limit
- Optional command-line argument to play a fixed, reproducible game

## How to run

```bash
javac NumberGuessingGame.java
java NumberGuessingGame
```

To play with a known secret number (handy for practice or testing):

```bash
java NumberGuessingGame 42
```

## Sample run

```
$ java NumberGuessingGame 42
Welcome to the Number Guessing Game!
I have picked a number between 1 and 100.
You have 10 attempts. Good luck!
Attempt 1/10 - enter your guess: 50
Too high! Try a smaller number.
Attempt 2/10 - enter your guess: 30
Too low! Try a bigger number.
Attempt 3/10 - enter your guess: 42
Correct! You guessed it in 3 attempts.
```
