package RockPaperScissors;

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
        public static Option getWinningOption(Option userOption) {
            switch (userOption) {
                case ROCK:
                    return Option.PAPER;
                case PAPER:
                    return Option.SCISSORS;
                case SCISSORS:
                    return Option.ROCK;
                default:
                    return null;
            }
        }
    }

    public static class Game {
        public void play(String input) {
            Option userOption = Option.fromString(input);

            if (userOption == null) {
                System.out.println("Invalid input");
                return;
            }

            Option winningOption = Rules.getWinningOption(userOption);

            System.out.println("Sorry, but the computer chose " + winningOption.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        String userInput = scanner.nextLine();
        game.play(userInput);
    }
}