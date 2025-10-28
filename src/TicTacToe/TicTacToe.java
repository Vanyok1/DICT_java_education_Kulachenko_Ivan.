package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String input = scanner.nextLine();
        GameBoard board = new GameBoard(input);
        board.displayBoard();
        board.makeMove(scanner);
        board.displayBoard();
    }
}

class GameBoard {
    private char[][] board;

    public GameBoard(String input) {
        board = new char[3][3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = input.charAt(index++);
            }
        }
    }

    public void displayBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public void makeMove(Scanner scanner) {
        while (true) {
            System.out.print("Enter the coordinates: ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (parts.length != 2) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int row, col;
            try {
                row = Integer.parseInt(parts[0]);
                col = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (row < 1 || row > 3 || col < 1 || col > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (board[row - 1][col - 1] != '_' && board[row - 1][col - 1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            board[row - 1][col - 1] = 'X';
            break;
        }
    }
}