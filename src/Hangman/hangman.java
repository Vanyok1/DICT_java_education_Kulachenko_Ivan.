package Hangman;

import java.util.Scanner;

public class hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("HANGMAN");
        System.out.println("The game will be available soon.");
        System.out.println();

        System.out.println("HANGMAN");
        System.out.println("Guess the word: > ");

        String correctWord = "java";
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase(correctWord)) {
            System.out.println("You survived!");
        } else {
            System.out.println("You lost!");
        }
    }
}