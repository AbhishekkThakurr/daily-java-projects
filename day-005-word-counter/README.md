# Day 5 — Word Counter

A small program that reads one sentence and analyses it: how many characters,
words, vowels and consonants it has, which word is the longest, how many times
a chosen word appears, and the sentence written backwards.

## Concepts used

- `String` methods: `length()`, `charAt()`, `toLowerCase()`, `trim()`,
  `equalsIgnoreCase()`, `split()`
- Splitting a sentence into words with `split("\\s+")`
- `for` and `for-each` loops over characters and words
- Breaking a job into small methods, one per counting task
- `Scanner` for line input (`nextLine()`) and word input (`next()`)

## How to run

```bash
javac WordCounter.java
java WordCounter
```

## Sample run

```text
Enter a sentence: Java is fun and Java is powerful

--- Analysis ---
Total characters (with spaces): 32
Characters (without spaces):   26
Words:                          7
Vowels:                         11
Consonants:                     15
Longest word:                   powerful
Enter a word to search for: Java
"Java" appears 2 time(s).
Reversed sentence: lufrewop si avaJ dna nuf si avaJ
```

## Try it yourself

- What happens if you enter only spaces? (Hint: `trim()` makes it empty.)
- Can you add a count of digits in the sentence?
- Can you make the search count a word even if it has a full stop after it,
  like "Java."?
