package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameBoard board = new GameBoard("_________");
        board.displayBoard();

        char currentPlayer = 'X';
        while (true) {
            board.makeMove(scanner, currentPlayer);
            board.displayBoard();
            String state = board.checkGameState();
            if (!state.equals("Game not finished")) {
                System.out.println(state);
                break;
            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
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

    public void makeMove(Scanner scanner, char player) {
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

            if (board[row - 1][col - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            board[row - 1][col - 1] = player;
            break;
        }
    }

    public String checkGameState() {
        if (isWinner('X')) return "X wins";
        if (isWinner('O')) return "O wins";
        if (hasEmptyCells()) return "Game not finished";
        return "Draw";
    }

    private boolean isWinner(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) return true;
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) return true;
        }
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) return true;
        return false;
    }

    private boolean hasEmptyCells() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == '_') return true;
            }
        }
        return false;
    }
}