public class GameContext {

    private GameState state;

    public GameContext() {
        state = new XTurnState();
    }

    public void setState(GameState newState) {
        this.state = newState;
    }

    public void next(Player p, boolean hasWon) {
        state.next(this, p, hasWon);
    }

    public boolean isGameOver() {
        return state.isGameOver();
    }

    public GameState getCurrentState() {
        return state;
    }
}
