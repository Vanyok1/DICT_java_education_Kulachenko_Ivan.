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

        System.out.println("Let me guess your age.");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");

        int remainder3 = Integer.parseInt(input.nextLine());
        int remainder5 = Integer.parseInt(input.nextLine());
        int remainder7 = Integer.parseInt(input.nextLine());

        int age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;

        System.out.println("Your age is " + age + "; that's a good time to start programming!");
    }
}