public class Main {
    public static void main(String[] args) {

        PlayerFactory factory = new SimplePlayerFactory();

        PlayerStrategy xStrat = new HumanPlayerStrategy("Player X");
        PlayerStrategy oStrat = new HumanPlayerStrategy("Player O");

        TicTacToeGame game = new TicTacToeGame(factory, xStrat, oStrat, 3, 3);

        game.addObserver(new ConsoleGameObserver());
        game.board.addObserver(new ConsoleBoardObserver());

        game.play();
    }
}
