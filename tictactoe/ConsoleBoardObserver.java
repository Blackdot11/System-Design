public class ConsoleBoardObserver implements BoardObserver {

    @Override
    public void onCellUpdate(Position pos, Symbol symbol) {
        System.out.println("[OBS] Board updated " + pos + " = " + symbol);
    }
}
