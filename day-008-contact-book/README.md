# Day 8 — Contact Book

A small menu-driven contact book that stores contacts by name and phone number.

## What it does

- Add a new contact (name + phone number)
- Find a contact by name
- Update a contact's phone number
- Delete a contact
- List all saved contacts
- Starts with 3 sample contacts (Aarav, Priya, Rohan) so the book is not empty

## Concepts used

- `HashMap<String, String>` — the name is the key, the phone number is the value
- `HashMap` methods: `put`, `get`, `containsKey`, `remove`, `isEmpty`, `size`, `entrySet`
- `Map.Entry` with a for-each loop to list all contacts
- `switch` expression (arrow syntax) for the menu
- `Scanner` with `nextLine()` for full-line input
- `try-catch` with `NumberFormatException` for safe number input
- Methods to keep each menu action separate and readable

Note: a `HashMap` does not keep contacts in insertion order, so the list
order can change from run to run. (Use a `LinkedHashMap` if order matters.)

## How to run

```bash
javac ContactBook.java
java ContactBook
```

## Sample run

```
===== Contact Book =====
1. Add contact
2. Find contact
3. Update contact
4. Delete contact
5. List all contacts
6. Exit
Choose an option (1-6): 1
Enter name: Ishita
Enter phone number: 98111 22334
Contact added: Ishita -> 98111 22334

===== Contact Book =====
...
Choose an option (1-6): 2
Enter name to find: Ishita
Ishita : 98111 22334

===== Contact Book =====
...
Choose an option (1-6): 5

--- All Contacts (4) ---
Priya : 98123 45678
Aarav : 98765 43210
Rohan : 99988 77665
Ishita : 98111 22334

===== Contact Book =====
...
Choose an option (1-6): 6
Goodbye! Your contacts are saved in memory only.
```

(Contacts are stored in memory only — they disappear when the program exits.
Saving them to a file is a good next step.)
