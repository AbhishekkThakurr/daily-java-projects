import java.util.Scanner;

/**
 * Word Counter — Day 5 of the Daily Java Projects.
 *
 * Reads one sentence from the user and analyses it:
 *  - total characters (with and without spaces)
 *  - number of words
 *  - number of vowels and consonants
 *  - the longest word
 *  - how many times a chosen word appears (case-insensitive)
 *  - the sentence written backwards
 */
public class WordCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine().trim();

        if (sentence.isEmpty()) {
            System.out.println("You entered an empty sentence. Nothing to count!");
            scanner.close();
            return;
        }

        String[] words = sentence.split("\\s+");

        int totalChars = sentence.length();
        int charsWithoutSpaces = countNonSpaceChars(sentence);
        int vowelCount = countVowels(sentence);
        int consonantCount = countConsonants(sentence);
        String longestWord = findLongestWord(words);

        System.out.println();
        System.out.println("--- Analysis ---");
        System.out.println("Total characters (with spaces): " + totalChars);
        System.out.println("Characters (without spaces):   " + charsWithoutSpaces);
        System.out.println("Words:                          " + words.length);
        System.out.println("Vowels:                         " + vowelCount);
        System.out.println("Consonants:                     " + consonantCount);
        System.out.println("Longest word:                   " + longestWord);

        System.out.print("Enter a word to search for: ");
        String searchWord = scanner.next();
        int occurrences = countOccurrences(words, searchWord);
        System.out.println("\"" + searchWord + "\" appears " + occurrences + " time(s).");

        System.out.println("Reversed sentence: " + reverseSentence(sentence));

        scanner.close();
    }

    /** Counts every character that is not a space. */
    public static int countNonSpaceChars(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ') {
                count++;
            }
        }
        return count;
    }

    /** Counts a, e, i, o, u (upper or lower case). */
    public static int countVowels(String text) {
        int count = 0;
        String lower = text.toLowerCase();
        for (int i = 0; i < lower.length(); i++) {
            char ch = lower.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    /** Counts letters that are not vowels. */
    public static int countConsonants(String text) {
        int count = 0;
        String lower = text.toLowerCase();
        for (int i = 0; i < lower.length(); i++) {
            char ch = lower.charAt(i);
            if (ch >= 'a' && ch <= 'z'
                    && ch != 'a' && ch != 'e' && ch != 'i' && ch != 'o' && ch != 'u') {
                count++;
            }
        }
        return count;
    }

    /** Returns the longest word in the array (first one wins a tie). */
    public static String findLongestWord(String[] words) {
        String longest = words[0];
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    /** Counts how many words equal the target, ignoring case. */
    public static int countOccurrences(String[] words, String target) {
        int count = 0;
        for (String word : words) {
            if (word.equalsIgnoreCase(target)) {
                count++;
            }
        }
        return count;
    }

    /** Builds the sentence backwards, character by character. */
    public static String reverseSentence(String text) {
        String reversed = "";
        for (int i = text.length() - 1; i >= 0; i--) {
            reversed += text.charAt(i);
        }
        return reversed;
    }
}
