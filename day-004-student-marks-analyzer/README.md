# Day 4 — Student Marks Analyzer

A small program that reads the marks of N subjects and reports the total,
average, highest and lowest marks, the overall grade, and a pass/fail result
for each subject.

## Concepts used

- **Arrays** — all the marks are stored in one `int[]` array
- **For loops** — reading the marks in, and scanning the array for totals and extremes
- **Enhanced for loop** (`for-each`) — adding up the marks
- **Methods** — one small method per job: total, average, highest, lowest, grade
- **if-else chains** — grade bands and the 40-marks pass/fail rule
- **Scanner input** and **printf** formatting for the average

## How to run

```bash
javac StudentMarksAnalyzer.java
java StudentMarksAnalyzer
```

## Sample run

```
How many subjects? 5
Enter marks for subject 1 (0-100): 78
Enter marks for subject 2 (0-100): 92
Enter marks for subject 3 (0-100): 65
Enter marks for subject 4 (0-100): 88
Enter marks for subject 5 (0-100): 54

----- Result -----
Total marks: 377 / 500
Average: 75.40%
Highest marks: 92
Lowest marks: 54
Grade: B

Subject-wise result:
Subject 1: 78 - Pass
Subject 2: 92 - Pass
Subject 3: 65 - Pass
Subject 4: 88 - Pass
Subject 5: 54 - Pass
```

## Try it yourself

- Change the pass mark from 40 to 33 and see how the subject-wise results change.
- Add a method that counts how many subjects scored above 90.
