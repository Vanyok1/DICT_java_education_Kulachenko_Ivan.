package RockPaperScissors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RockPaperScissors {

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
        private final List<String> options;

        public Rules(List<String> options) {
            this.options = options;
        }

        public String getResult(String user, String computer) {
            if (user.equals(computer)) return "draw";

            int index = options.indexOf(user);
            List<String> shifted = new ArrayList<>();
            shifted.addAll(options.subList(index + 1, options.size()));
            shifted.addAll(options.subList(0, index));

            int half = shifted.size() / 2;
            List<String> loseTo = shifted.subList(0, half);
            List<String> winAgainst = shifted.subList(half, shifted.size());

            if (loseTo.contains(computer)) return "lose";
            else return "win";
        }
    }

    public static class Game {
        private final Random random = new Random();
        private final Player player;
        private final Rules rules;
        private final List<String> options;

        public Game(Player player, List<String> options) {
            this.player = player;
            this.options = options;
            this.rules = new Rules(options);
        }

        private String getRandomOption() {
            return options.get(random.nextInt(options.size()));
        }

        public void playRound(String input) {
            if (!options.contains(input)) {
                System.out.println("Invalid input");
                return;
            }

            String computer = getRandomOption();
            String result = rules.getResult(input, computer);

            switch (result) {
                case "win":
                    player.addWin();
                    System.out.println("Well done. The computer chose " + computer + " and failed");
                    break;
                case "draw":
                    player.addDraw();
                    System.out.println("There is a draw (" + computer + ")");
                    break;
                case "lose":
                    System.out.println("Sorry, but the computer chose " + computer);
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

        String optionsInput = scanner.nextLine();

        List<String> options;
        if (optionsInput.isEmpty()) {
            options = Arrays.asList("rock", "paper", "scissors");
        } else {
            options = Arrays.asList(optionsInput.split(","));
            for (int i = 0; i < options.size(); i++) {
                options.set(i, options.get(i).trim());
            }
        }

        System.out.println("Okay, let's start");

        Player player = new Player(name, rating);
        Game game = new Game(player, options);

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

            game.playRound(input);
        }
    }
}