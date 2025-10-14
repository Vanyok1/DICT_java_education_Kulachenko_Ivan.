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

        int attempts = 8;

        while (attempts > 0) {
            System.out.println(hint);
            System.out.print("Input a letter: > ");
            String letter = scanner.nextLine();

            if (correctWord.contains(letter)) {
                for (int i = 0; i < correctWord.length(); i++) {
                    if (correctWord.charAt(i) == letter.charAt(0)) {
                        hint.setCharAt(i, letter.charAt(0));
                    }
                }
            } else {
                System.out.println("That letter doesn't appear in the word");
            }

            attempts--;

            if (hint.toString().equals(correctWord)) {
                System.out.println(hint);
                break;
            }
        }

        System.out.println("Thanks for playing!");
        System.out.println("We'll see how well you did in the next stage");

        scanner.close();
    }
}
