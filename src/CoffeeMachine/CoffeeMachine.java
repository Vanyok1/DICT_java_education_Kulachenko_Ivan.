package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachine {
    private int water = 400;
    private int milk = 540;
    private int beans = 120;
    private int cups = 9;
    private int money = 550;

    private enum State {
        WAITING_ACTION, BUYING, FILLING_WATER, FILLING_MILK, FILLING_BEANS, FILLING_CUPS
    }

    private State state = State.WAITING_ACTION;
    private String buyChoice = "";

    public void process(String input) {
        switch (state) {
            case WAITING_ACTION:
                switch (input) {
                    case "buy":
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                        state = State.BUYING;
                        break;
                    case "fill":
                        System.out.println("Write how many ml of water do you want to add:");
                        state = State.FILLING_WATER;
                        break;
                    case "take":
                        System.out.println("I gave you " + money);
                        money = 0;
                        break;
                    case "remaining":
                        printState();
                        break;
                    case "exit":
                        state = null;
                        break;
                    default:
                        System.out.println("Unknown action.");
                }
                break;

            case BUYING:
                buyChoice = input;
                if (buyChoice.equals("back")) {
                    state = State.WAITING_ACTION;
                } else {
                    int waterNeeded = 0, milkNeeded = 0, beansNeeded = 0, cost = 0;
                    switch (buyChoice) {
                        case "1":
                            waterNeeded = 250;
                            beansNeeded = 16;
                            cost = 4;
                            break;
                        case "2":
                            waterNeeded = 350;
                            milkNeeded = 75;
                            beansNeeded = 20;
                            cost = 7;
                            break;
                        case "3":
                            waterNeeded = 200;
                            milkNeeded = 100;
                            beansNeeded = 12;
                            cost = 6;
                            break;
                    }
                    if (water >= waterNeeded && milk >= milkNeeded && beans >= beansNeeded && cups >= 1) {
                        System.out.println("I have enough resources, making you a coffee!");
                        water -= waterNeeded;
                        milk -= milkNeeded;
                        beans -= beansNeeded;
                        cups -= 1;
                        money += cost;
                    } else {
                        if (water < waterNeeded) System.out.println("Sorry, not enough water!");
                        else if (milk < milkNeeded) System.out.println("Sorry, not enough milk!");
                        else if (beans < beansNeeded) System.out.println("Sorry, not enough coffee beans!");
                        else System.out.println("Sorry, not enough disposable cups!");
                    }
                    state = State.WAITING_ACTION;
                }
                break;

            case FILLING_WATER:
                water += Integer.parseInt(input);
                System.out.println("Write how many ml of milk do you want to add:");
                state = State.FILLING_MILK;
                break;
            case FILLING_MILK:
                milk += Integer.parseInt(input);
                System.out.println("Write how many grams of coffee beans do you want to add:");
                state = State.FILLING_BEANS;
                break;
            case FILLING_BEANS:
                beans += Integer.parseInt(input);
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                state = State.FILLING_CUPS;
                break;
            case FILLING_CUPS:
                cups += Integer.parseInt(input);
                state = State.WAITING_ACTION;
                break;
        }
    }

    private void printState() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");
    }

    public boolean isRunning() {
        return state != null;
    }

    public boolean isWaitingAction() {
        return state == State.WAITING_ACTION;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine machine = new CoffeeMachine();

        while (machine.isRunning()) {
            if (machine.isWaitingAction()) {
                System.out.println("Write action (buy, fill, take, remaining, exit):");
            }
            String input = scanner.nextLine();
            machine.process(input);
        }
    }
}