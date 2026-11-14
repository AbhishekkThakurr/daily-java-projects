# Day 2 — Simple Calculator

A menu-driven calculator that performs basic arithmetic: add, subtract, multiply, divide, and power. It keeps running until you choose Exit.

## What it covers

- **Methods** — one small method per operation (`add`, `subtract`, `multiply`, `divide`, `power`)
- **switch statement** — picks the operation from the menu choice
- **do-while loop** — shows the menu again after every calculation
- **Scanner input** — reads the menu choice and the two numbers
- **Exception handling** — dividing by zero throws an `ArithmeticException`, which is caught and shown as a friendly error instead of crashing the program
- **printf formatting** — results are printed with two decimal places

## How to run

```bash
javac SimpleCalculator.java
java SimpleCalculator
```

## Sample run

```
--- Simple Calculator ---
1. Add (+)
2. Subtract (-)
3. Multiply (*)
4. Divide (/)
5. Power (^)
0. Exit
Choose an option: 1
Enter first number: 12
Enter second number: 8
12.00 + 8.00 = 20.00

--- Simple Calculator ---
Choose an option: 4
Enter first number: 20
Enter second number: 0
Error: Cannot divide by zero.

--- Simple Calculator ---
Choose an option: 5
Enter first number: 2
Enter second number: 3
2.00 ^ 3.00 = 8.00

--- Simple Calculator ---
Choose an option: 0
Goodbye!
```
