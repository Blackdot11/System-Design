import java.util.Scanner;

public class HumanPlayerStrategy implements PlayerStrategy {

    private Scanner scanner = new Scanner(System.in);
    private String name;

    public HumanPlayerStrategy(String name) {
        this.name = name;
    }

    @Override
    public Position makeMove(Board board) {
        while (true) {
            System.out.print(name + " enter row and col: ");
            int r = scanner.nextInt();
            int c = scanner.nextInt();

            Position p = new Position(r, c);

            if (board.isValidMove(p)) {
                return p;
            }
            System.out.println("Invalid move, try again.");
        }
    }
}
