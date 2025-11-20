import java.util.ArrayList;
import java.util.List;

public class TicTacToeGame {

    public Board board;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private GameContext gameContext;

    private List<GameObserver> observers = new ArrayList<>();

    public void addObserver(GameObserver obs) {
        observers.add(obs);
    }

    private void notifyMove(Player p, Position pos) {
        for (GameObserver obs : observers)
            obs.onMove(p, pos);
    }

    private void notifyTurn(Player p) {
        for (GameObserver obs : observers)
            obs.onTurnChange(p);
    }

    private void notifyEnd(String result) {
        for (GameObserver obs : observers)
            obs.onGameEnd(result);
    }

    public TicTacToeGame(PlayerFactory factory,
                         PlayerStrategy xStrategy,
                         PlayerStrategy oStrategy,
                         int rows,
                         int cols) {

        board = new Board(rows, cols);

        playerX = factory.createPlayer(Symbol.X, xStrategy);
        playerO = factory.createPlayer(Symbol.O, oStrategy);

        currentPlayer = playerX;
        gameContext = new GameContext();
    }

    public void play() {

        notifyTurn(currentPlayer);

        do {
            board.printBoard();

            Position pos = currentPlayer.getPlayerStrategy().makeMove(board);
            board.makeMove(pos, currentPlayer.getSymbol());
            notifyMove(currentPlayer, pos);

            boolean hasWon = board.hasWon(currentPlayer.getSymbol());

            if (!hasWon && board.isDraw()) {
                gameContext.setState(new DrawState());
            } else {
                gameContext.next(currentPlayer, hasWon);
            }

            switchTurn();
            notifyTurn(currentPlayer);

        } while (!gameContext.isGameOver());

        String result = resultText();
        notifyEnd(result);
        System.out.println(result);
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
    }

    private String resultText() {
        GameState s = gameContext.getCurrentState();

        if (s instanceof XWonState) return "Player X wins!";
        if (s instanceof OWonState) return "Player O wins!";
        return "It's a draw!";
    }
}
