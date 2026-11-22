# Day 10 — Quiz Game

A multiple-choice quiz game about Java. The program asks 5 questions,
checks each answer, keeps the score, and shows a result message at the
end based on how well you did.

## What it covers

- **Classes and objects** — a `Question` class holds the question text,
  four options and the correct answer
- **ArrayList** — the quiz questions are stored in an `ArrayList<Question>`
  and played with a for-each loop
- **Scanner input** — reading the player's answer with `nextLine()`
- **Input validation** — `Integer.parseInt` inside a `try-catch` block, so
  typing `abc` or `9` never crashes the game; it just asks again
- **Methods** — `buildQuestions()`, `readAnswer()` and `showResult()` keep
  `main()` short and readable
- **if-else chains** — different result messages for different scores

## How to run

```bash
javac QuizGame.java
java QuizGame
```

## Sample run

```text
==============================
   Welcome to the Quiz Game!
==============================
Answer each question by typing 1, 2, 3 or 4.
There are 5 questions. Good luck!

Q1. Which keyword is used to create an object in Java?
   1) class
   2) new
   3) this
   4) object
Your answer (1-4): 2
Correct! Well done.

Q2. What is the size of an int in Java?
   1) 2 bytes
   2) 4 bytes
   3) 8 bytes
   4) 16 bytes
Your answer (1-4): 2
Correct! Well done.

Q3. Which loop checks its condition AFTER running the body once?
   1) for loop
   2) while loop
   3) do-while loop
   4) for-each loop
Your answer (1-4): 3
Correct! Well done.

Q4. Which of these is used to store a list of items that can grow?
   1) Array
   2) ArrayList
   3) String
   4) int
Your answer (1-4): 2
Correct! Well done.

Q5. What does JVM stand for?
   1) Java Virtual Machine
   2) Java Variable Method
   3) Java Verified Module
   4) Joint Virtual Machine
Your answer (1-4): 1
Correct! Well done.

==============================
   Quiz finished!
   Your score: 5 / 5
==============================
Perfect score! You are a Java star.
```

## Try it yourself

- Type a wrong answer and see the program show the correct one.
- Type `abc` or `9` as an answer — the game asks again instead of crashing.
- Add your own question in `buildQuestions()` and play again.
