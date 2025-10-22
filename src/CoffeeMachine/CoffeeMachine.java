package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void printState(int water, int milk, int beans, int cups, int money) {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");
    }

    public static void buyCoffee(Scanner input, int[] resources) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        int choice = input.nextInt();

        int waterNeeded = 0;
        int milkNeeded = 0;
        int beansNeeded = 0;
        int cost = 0;

        switch (choice) {
            case 1:
                waterNeeded = 250;
                milkNeeded = 0;
                beansNeeded = 16;
                cost = 4;
                break;
            case 2:
                waterNeeded = 350;
                milkNeeded = 75;
                beansNeeded = 20;
                cost = 7;
                break;
            case 3:
                waterNeeded = 200;
                milkNeeded = 100;
                beansNeeded = 12;
                cost = 6;
                break;
            default:
                System.out.println("Unknown coffee type.");
                return;
        }

        if (resources[0] >= waterNeeded && resources[1] >= milkNeeded && resources[2] >= beansNeeded && resources[3] >= 1) {
            resources[0] -= waterNeeded;
            resources[1] -= milkNeeded;
            resources[2] -= beansNeeded;
            resources[3] -= 1;
            resources[4] += cost;
        } else {
            System.out.println("Not enough ingredients!");
        }
    }

    public static void fillMachine(Scanner input, int[] resources) {
        System.out.println("Write how many ml of water you want to add:");
        resources[0] += input.nextInt();

        System.out.println("Write how many ml of milk you want to add:");
        resources[1] += input.nextInt();

        System.out.println("Write how many grams of coffee beans you want to add:");
        resources[2] += input.nextInt();

        System.out.println("Write how many disposable coffee cups you want to add:");
        resources[3] += input.nextInt();
    }

    public static void takeMoney(int[] resources) {
        System.out.println("I gave you " + resources[4]);
        resources[4] = 0;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int water = 400;
        int milk = 540;
        int beans = 120;
        int cups = 9;
        int money = 550;

        int[] resources = {water, milk, beans, cups, money};

        printState(resources[0], resources[1], resources[2], resources[3], resources[4]);

        System.out.println("Write action (buy, fill, take):");
        String action = input.next();

        switch (action) {
            case "buy":
                buyCoffee(input, resources);
                break;
            case "fill":
                fillMachine(input, resources);
                break;
            case "take":
                takeMoney(resources);
                break;
            default:
                System.out.println("Unknown action.");
        }

        printState(resources[0], resources[1], resources[2], resources[3], resources[4]);
    }
}