# Day 11 — Rock Paper Scissors Game

A best-of-rounds Rock Paper Scissors game against the computer. The first
player to win 3 rounds takes the match.

## What it does

- Asks you to pick `rock`, `paper` or `scissors` each round
- The computer picks its move at random
- Keeps score round by round and announces the match winner
- Typing `quit` abandons the match at any time
- Invalid input is rejected with a message and the game asks again — it never crashes

## Concepts used

- `enum` — the three moves (`ROCK`, `PAPER`, `SCISSORS`) as a fixed, type-safe set of values
- `Random` — the computer's move (`Choice.values()` + `nextInt`)
- `while` loop — the match runs until someone reaches 3 round wins
- `switch` expression — turning the player's typed word into a `Choice`
- Methods — `readPlayerChoice`, `randomChoice` and `decideWinner` keep `main` short and readable
- Input validation — `trim()` + `toLowerCase()` so `Rock`, ` ROCK ` and `rock` all work
- Constants — `ROUNDS_TO_WIN` so the target score is easy to change in one place

## How to run

```bash
javac RockPaperScissors.java
java RockPaperScissors
```

## Sample run

```
=== Rock Paper Scissors ===
First to win 3 rounds takes the match!
Type rock, paper or scissors (or 'quit' to stop).

--- Round 1 ---
Your move (rock/paper/scissors): rock
You chose: ROCK
Computer chose: PAPER
Computer wins this round!
Score - You: 0 | Computer: 1

--- Round 2 ---
Your move (rock/paper/scissors): paper
You chose: PAPER
Computer chose: PAPER
It's a tie! No points awarded.
Score - You: 0 | Computer: 1

--- Round 3 ---
Your move (rock/paper/scissors): scissors
You chose: SCISSORS
Computer chose: ROCK
Computer wins this round!
Score - You: 0 | Computer: 2

--- Round 4 ---
Your move (rock/paper/scissors): rock
You chose: ROCK
Computer chose: ROCK
It's a tie! No points awarded.
Score - You: 0 | Computer: 2

--- Round 5 ---
Your move (rock/paper/scissors): paper
You chose: PAPER
Computer chose: ROCK
You win this round!
Score - You: 1 | Computer: 2

--- Round 6 ---
Your move (rock/paper/scissors): scissors
You chose: SCISSORS
Computer chose: ROCK
Computer wins this round!
Score - You: 1 | Computer: 3

Computer won the match 3-1. Better luck next time!
```

(The computer's moves are random, so your run will differ — the rules and the
scorekeeping are the same every time.)
