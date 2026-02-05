package Hangman;

import java.util.Scanner;
import java.util.Random;

public class hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("HANGMAN");

        while (true) {
            System.out.print("Type \"play\" to play the game, \"exit\" to quit: > ");
            String menu = scanner.nextLine();

            if (menu.equals("exit")) {
                break;
            } else if (!menu.equals("play")) {
                continue;
            }

            String[] words = {"python", "java", "javascript", "kotlin"};
            String correctWord = words[random.nextInt(words.length)];

            StringBuilder hint = new StringBuilder("-".repeat(correctWord.length()));
            String guessedLetters = "";

            int attempts = 8;

            while (attempts > 0) {
                System.out.println(hint);
                System.out.print("Input a letter: > ");
                String letter = scanner.nextLine();

                if (letter.length() != 1) {
                    System.out.println("You should input a single letter");
                    continue;
                }

                if (!letter.matches("[a-z]")) {
                    System.out.println("Please enter a lowercase English letter");
                    continue;
                }

                if (guessedLetters.contains(letter)) {
                    System.out.println("You've already guessed this letter");
                    continue;
                }

                guessedLetters += letter;

                if (correctWord.contains(letter)) {
                    for (int i = 0; i < correctWord.length(); i++) {
                        if (correctWord.charAt(i) == letter.charAt(0)) {
                            hint.setCharAt(i, letter.charAt(0));
                        }
                    }
                } else {
                    System.out.println("That letter doesn't appear in the word");
                    attempts--;
                }

                if (hint.toString().equals(correctWord)) {
                    System.out.println("You guessed the word " + correctWord + "!");
                    System.out.println("You survived!");
                    break;
                }
            }

            if (!hint.toString().equals(correctWord) && attempts == 0) {
                System.out.println("You lost!");
            }
        }

        scanner.close();
    }
}
