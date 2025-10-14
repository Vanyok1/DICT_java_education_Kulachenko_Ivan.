package Hangman;

import java.util.Scanner;
import java.util.Random;

public class hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("HANGMAN");
        System.out.println("The game will be available soon.");
        System.out.println();

        String [] words = {"python", "java", "javascript", "kotlin" };
        String correctWord = words[random.nextInt(words.length)];

        boolean correct = false;

        while (!correct) {
            System.out.println("HANGMAN");
            System.out.println("Guess the word: > ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase(correctWord)) {
                System.out.println("You survived!");
                correct = true;
            } else {
                System.out.println("You lost!");
            }
        }

        scanner.close();
    }
}