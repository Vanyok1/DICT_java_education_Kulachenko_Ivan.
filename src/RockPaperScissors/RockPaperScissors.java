package RockPaperScissors;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public enum Option {
        ROCK("rock"),
        PAPER("paper"),
        SCISSORS("scissors");

        private final String value;

        Option(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Option fromString(String input) {
            for (Option o : Option.values()) {
                if (o.value.equalsIgnoreCase(input)) {
                    return o;
                }
            }
            return null;
        }
    }

    public static class Rules {
        public static String determineResult(Option user, Option computer) {
            if (user == computer) return "draw";
            switch (user) {
                case ROCK:
                    return (computer == Option.SCISSORS) ? "win" : "lose";
                case PAPER:
                    return (computer == Option.ROCK) ? "win" : "lose";
                case SCISSORS:
                    return (computer == Option.PAPER) ? "win" : "lose";
                default:
                    return "lose";
            }
        }
    }

    public static class Game {
        private final Random random = new Random();

        private Option getRandomOption() {
            Option[] options = Option.values();
            return options[random.nextInt(options.length)];
        }

        public void play(String input) {
            Option userOption = Option.fromString(input);
            if (userOption == null) {
                System.out.println("Invalid input");
                return;
            }

            Option computerOption = getRandomOption();
            String result = Rules.determineResult(userOption, computerOption);

            switch (result) {
                case "win":
                    System.out.println("Well done. The computer chose " + computerOption.getValue() + " and failed");
                    break;
                case "draw":
                    System.out.println("There is a draw (" + computerOption.getValue() + ")");
                    break;
                case "lose":
                    System.out.println("Sorry, but the computer chose " + computerOption.getValue());
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        game.play(scanner.nextLine());
    }
}