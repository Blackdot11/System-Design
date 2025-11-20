public class ConsoleGameObserver implements GameObserver {

    @Override
    public void onMove(Player player, Position position) {
        System.out.println("[OBS] " + player.getSymbol() + " played at " + position);
    }

    @Override
    public void onTurnChange(Player nextPlayer) {
        System.out.println("[OBS] Now playing: " + nextPlayer.getSymbol());
    }

    @Override
    public void onGameEnd(String result) {
        System.out.println("[OBS] Game ended -> " + result);
    }
}
