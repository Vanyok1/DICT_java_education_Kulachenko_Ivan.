package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        System.out.println("Starting to make a coffee");
        System.out.println("Grinding coffee beans");
        System.out.println("Boiling water");
        System.out.println("Mixing boiled water with crushed coffee beans");
        System.out.println("Pouring coffee into the cup");
        System.out.println("Pouring some milk into the cup");
        System.out.println("Coffee is ready!");
        System.out.println();

        Scanner input = new Scanner(System.in);

        System.out.println("Write how many cups of coffee you will need:");
        int cups = input.nextInt();

        int water = cups * 200;
        int milk = cups * 50;
        int coffeeBeans = cups * 15;

        System.out.println("For " + cups + " cups of coffee you will need:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffeeBeans + " g of coffee beans");

        System.out.println("Write how many ml of water the coffee machine has:");
        int Water = input.nextInt();

        System.out.println("Write how many ml of milk the coffee machine has:");
        int Milk = input.nextInt();

        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int CoffeeBeans = input.nextInt();

        System.out.println("Write how many cups of coffee you will need:");
        int Cups = input.nextInt();

        int maxCupsByWater = Water / 200;
        int maxCupsByMilk = Milk / 50;
        int maxCupsByBeans = CoffeeBeans / 15;

        int maxCupsPossible = Math.min(maxCupsByWater, Math.min(maxCupsByMilk, maxCupsByBeans));

        if (maxCupsPossible == Cups) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (maxCupsPossible > Cups) {
            int extra = maxCupsPossible - Cups;
            System.out.println("Yes, I can make that amount of coffee (and even " + extra + " more than that)");
        } else {
            System.out.println("No, I can make only " + maxCupsPossible + " cups of coffee");
        }
    }
}