public class OTurnState implements GameState {

    @Override
    public void next(GameContext context, Player player, boolean hasWon) {
        if (hasWon)
            context.setState(new OWonState());
        else
            context.setState(new XTurnState());
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
