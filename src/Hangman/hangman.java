package Hangman;

import java.util.Scanner;
import java.util.Random;

public class hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("HANGMAN");
        System.out.println();

        String[] words = {"python", "java", "javascript", "kotlin"};
        String correctWord = words[random.nextInt(words.length)];

        StringBuilder hint = new StringBuilder("-".repeat(correctWord.length()));
        String guessedLetters = "";

        int attempts = 8;

        while (attempts > 0) {
            System.out.println(hint);
            System.out.print("Input a letter: > ");
            String letter = scanner.nextLine();

            if (guessedLetters.contains(letter)) {
                System.out.println("No improvements");
                attempts--;
            } else if (!correctWord.contains(letter)) {
                System.out.println("That letter doesn't appear in the word");
                attempts--;
            } else {
                for (int i = 0; i < correctWord.length(); i++) {
                    if (correctWord.charAt(i) == letter.charAt(0)) {
                        hint.setCharAt(i, letter.charAt(0));
                    }
                }
            }

            guessedLetters += letter;

            if (hint.toString().equals(correctWord)) {
                System.out.println(hint);
                System.out.println("You guessed the word!");
                System.out.println("You survived!");
                break;
            }
        }

        if (!hint.toString().equals(correctWord) && attempts == 0) {
            System.out.println("You lost!");
        }

        scanner.close();
    }
}
