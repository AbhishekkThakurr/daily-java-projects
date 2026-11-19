# Day 7 — Library Book Manager

A small menu-driven library app: add books, list them, search by title,
and borrow / return a book. It builds on Day 6 (classes and objects) by
storing the books in an `ArrayList` — a collection that grows and shrinks
as books are added.

## Concepts used

- **Classes and objects** — a `Book` class with fields (`title`, `author`,
  `borrowed`), a constructor, and methods (`borrow()`, `giveBack()`,
  `status()`, `toString()`).
- **ArrayList** — `java.util.ArrayList<Book>` holds all the books; `add()`,
  `get()`, `size()` and `isEmpty()` manage the list.
- **for-each loop** — searching through the books.
- **Methods** — `findByTitle()` is reused by both borrow and return.
- **switch expression with `->`** — clean menu handling (Java 14+).
- **Scanner** — reading menu choices and text input (`nextInt()` +
  `nextLine()` to consume the leftover newline).

## How to run

```bash
javac LibraryBookManager.java
java LibraryBookManager
```

## Sample run

```text
--- Library Book Manager ---
1. Add a book
2. List all books
3. Search by title
4. Borrow a book
5. Return a book
6. Exit
Choose an option: 2
Books in the library:
1. "The Alchemist" by Paulo Coelho — Available
2. "Clean Code" by Robert C. Martin — Available

--- Library Book Manager ---
Choose an option: 4
Title to borrow: Clean Code
You borrowed "Clean Code". Enjoy reading!

--- Library Book Manager ---
Choose an option: 2
Books in the library:
1. "The Alchemist" by Paulo Coelho — Available
2. "Clean Code" by Robert C. Martin — Borrowed

--- Library Book Manager ---
Choose an option: 5
Title to return: Clean Code
Thank you for returning "Clean Code".

--- Library Book Manager ---
Choose an option: 2
Books in the library:
1. "The Alchemist" by Paulo Coelho — Available
2. "Clean Code" by Robert C. Martin — Available

--- Library Book Manager ---
Choose an option: 6
Goodbye! Total books in library: 2
```

## Try it yourself

- Add a new book (option 1) and list the books again — the `ArrayList`
  grows.
- Try borrowing the same book twice — the second attempt is refused.
- Try searching for a book that does not exist.
