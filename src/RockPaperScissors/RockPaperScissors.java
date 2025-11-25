package RockPaperScissors;

import java.io.File;
import java.io.FileNotFoundException;
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
                if (o.value.equalsIgnoreCase(input)) return o;
            }
            return null;
        }
    }

    public static class Player {
        private final String name;
        private int rating;

        public Player(String name, int rating) {
            this.name = name;
            this.rating = rating;
        }

        public void addWin() {
            rating += 100;
        }

        public void addDraw() {
            rating += 50;
        }

        public int getRating() {
            return rating;
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
        private final Player player;

        public Game(Player player) {
            this.player = player;
        }

        private Option getRandomOption() {
            Option[] options = Option.values();
            return options[random.nextInt(options.length)];
        }

        public void playRound(String input) {
            Option userOption = Option.fromString(input);
            if (userOption == null) {
                System.out.println("Invalid input");
                return;
            }

            Option computerOption = getRandomOption();
            String result = Rules.determineResult(userOption, computerOption);

            switch (result) {
                case "win":
                    player.addWin();
                    System.out.println("Well done. The computer chose " + computerOption.getValue() + " and failed");
                    break;
                case "draw":
                    player.addDraw();
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

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello, " + name);

        int rating = 0;

        try {
            Scanner fileScanner = new Scanner(new File("rating.txt"));
            while (fileScanner.hasNext()) {
                String playerName = fileScanner.next();
                int playerRating = fileScanner.nextInt();
                if (playerName.equals(name)) rating = playerRating;
            }
        } catch (FileNotFoundException ignored) {}

        Player player = new Player(name, rating);
        Game game = new Game(player);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("!exit")) {
                System.out.println("Bye!");
                break;
            }

            if (input.equals("!rating")) {
                System.out.println("Your rating: " + player.getRating());
                continue;
            }

            if (Option.fromString(input) != null) {
                game.playRound(input);
            } else {
                System.out.println("Invalid input");
            }
        }
    }
}