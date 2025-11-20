public interface BoardObserver {
    void onCellUpdate(Position pos, Symbol symbol);
}
