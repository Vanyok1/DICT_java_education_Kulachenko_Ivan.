package ChatBot;

import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        System.out.println("Hello! My name is Ivan.");
        System.out.println("I was created in 2025.");
        System.out.println("Please, remind me your name.");

        Scanner input = new Scanner(System.in);
        String name = input.nextLine();

        System.out.println("What a great name you have, " + name + "!");
    }
}