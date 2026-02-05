package ChatBot;

import java.awt.desktop.SystemEventListener;
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

        System.out.println("Now I will prove to you that I can count to any number you want!");
        int number = Integer.parseInt(input.nextLine());
        for (int i = 0; i <= number; i++) {
            System.out.println(i + "!");
        }

        System.out.println("Choose the correct answer");
        System.out.println("Which method of declaring a variable in Java is correct?");
        System.out.println("1. int number;");
        System.out.println("2. number int;");
        System.out.println("3. integer number;");
        System.out.println("4. num int;");

        int answer;
        do {
            answer = Integer.parseInt(input.nextLine());
            if (answer != 1) {
                System.out.println("Wrong");
            }
        } while (answer != 1);

        System.out.println("Correct");

        System.out.println("Goodbye, have a nice day!");

        input.close();
    }
}