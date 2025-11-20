import java.util.ArrayList;
import java.util.List;

public class Board {

    private Symbol[][] grid;
    private int rows, cols;

    private List<BoardObserver> observers = new ArrayList<>();

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Symbol[rows][cols];

        for (int r=0; r<rows; r++)
            for (int c=0; c<cols; c++)
                grid[r][c] = Symbol.EMPTY;
    }

    public void addObserver(BoardObserver obs) {
        observers.add(obs);
    }

    private void notifyUpdate(Position pos, Symbol s) {
        for (BoardObserver obs : observers)
            obs.onCellUpdate(pos, s);
    }

    public boolean isValidMove(Position p) {
        return p.row >= 0 && p.row < rows &&
               p.col >= 0 && p.col < cols &&
               grid[p.row][p.col] == Symbol.EMPTY;
    }

    public void makeMove(Position pos, Symbol s) {
        grid[pos.row][pos.col] = s;
        notifyUpdate(pos, s);
    }

    public boolean hasWon(Symbol s) {
        for (int r=0; r<rows; r++) {
            if (grid[r][0] == s && grid[r][1] == s && grid[r][2] == s)
                return true;
        }
        for (int c=0; c<cols; c++) {
            if (grid[0][c] == s && grid[1][c] == s && grid[2][c] == s)
                return true;
        }
        return (grid[0][0] == s && grid[1][1] == s && grid[2][2] == s)
            || (grid[0][2] == s && grid[1][1] == s && grid[2][0] == s);
    }

    public boolean isDraw() {
        for (int r=0; r<rows; r++)
            for (int c=0; c<cols; c++)
                if (grid[r][c] == Symbol.EMPTY)
                    return false;
        return true;
    }

    public void printBoard() {
        for (int r=0; r<rows; r++) {
            for (int c=0; c<cols; c++) {
                System.out.print(" " + grid[r][c] + " ");
                if (c < cols - 1) System.out.print("|");
            }
            System.out.println();
            if (r < rows - 1)
                System.out.println("---+---+---");
        }
    }
}
