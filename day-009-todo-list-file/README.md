# Day 9 — Todo List with File Saving

A small to-do list that **remembers your tasks**. Every time you add, finish,
or delete a task, the list is saved to a text file (`tasks.txt`). The next
time you run the program, your tasks are loaded back automatically — nothing
is lost when you exit.

## What it covers

- **File handling** — reading a file with `BufferedReader` / `FileReader` and
  writing with `BufferedWriter` / `FileWriter`
- **try-with-resources** — files close themselves, even if an error happens
- **Exceptions** — `IOException` when the file is missing or cannot be saved,
  `NumberFormatException` for bad task numbers
- **ArrayList** of custom objects (a small `Task` class with `text` and `done`)
- **String methods** — `split("|", 2)` to read each saved line back into two parts

## How the save file works

Each task is stored on one line as `done|task text`, for example:

```text
true|Finish Java homework
false|Buy groceries
```

## How to run

```bash
javac TodoList.java
java TodoList
```

## Sample run

```text
No saved tasks found. Starting fresh.
=== Todo List ===
0 task(s) loaded from tasks.txt

1. Add a task
2. List all tasks
3. Mark a task as done
4. Delete a task
5. Save and exit
Choose an option: 1
Enter the task: Finish Java homework
Task added.

1. Add a task
2. List all tasks
3. Mark a task as done
4. Delete a task
5. Save and exit
Choose an option: 1
Enter the task: Buy groceries
Task added.

Choose an option: 2

Your tasks:
1. [ ] Finish Java homework
2. [ ] Buy groceries

Choose an option: 3

Your tasks:
1. [ ] Finish Java homework
2. [ ] Buy groceries
Enter the task number to mark as done: 1
Task marked as done.

Choose an option: 2

Your tasks:
1. [x] Finish Java homework
2. [ ] Buy groceries

Choose an option: 5
Tasks saved. Goodbye!
```

Run it again and the two tasks are loaded back from `tasks.txt`:

```text
=== Todo List ===
2 task(s) loaded from tasks.txt
...
1. [x] Finish Java homework
2. [ ] Buy groceries
```
