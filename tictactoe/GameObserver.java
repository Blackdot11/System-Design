public interface GameObserver {
    void onMove(Player player, Position position);
    void onTurnChange(Player nextPlayer);
    void onGameEnd(String result);
}
