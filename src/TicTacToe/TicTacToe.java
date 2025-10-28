package TicTacToe;

public class TicTacToe {
    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        board.displayBoard();
    }
}

class GameBoard {
    private char[][] board;

    public GameBoard() {
        board = new char[][]{
                {'X', 'O', 'X'},
                {'O', 'X', 'O'},
                {'X', 'X', 'O'}
        };
    }

    public void displayBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}