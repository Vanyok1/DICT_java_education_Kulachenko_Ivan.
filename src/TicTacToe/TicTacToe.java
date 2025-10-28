package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String input = scanner.nextLine();
        GameBoard board = new GameBoard(input);
        board.displayBoard();
        System.out.println(board.checkGameState());
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

    public String checkGameState() {
        boolean xWins = isWinner('X');
        boolean oWins = isWinner('O');
        int countX = countSymbol('X');
        int countO = countSymbol('O');
        boolean hasEmpty = hasEmptyCells();

        if (Math.abs(countX - countO) > 1 || (xWins && oWins)) {
            return "Impossible";
        } else if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else if (hasEmpty) {
            return "Game not finished";
        } else {
            return "Draw";
        }
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

    private int countSymbol(char symbol) {
        int count = 0;
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == symbol) count++;
            }
        }
        return count;
    }

    private boolean hasEmptyCells() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == '_' || cell == ' ') return true;
            }
        }
        return false;
    }
}