public class XTurnState implements GameState {

    @Override
    public void next(GameContext context, Player player, boolean hasWon) {
        if (hasWon)
            context.setState(new XWonState());
        else
            context.setState(new OTurnState());
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
