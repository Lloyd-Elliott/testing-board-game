public interface BoardStratergy {
    void setupBoard();
    void movePlayer(int moves, RulesStratergy rulesStratergy);
    boolean hasWinner();

    int getCurrentIndex1();
    int getCurrentIndex2();
    int getEndIndex1();
    int getEndIndex2();
    void setGameOver(boolean value);

    void addObserver(BoardObserver obs);
    void notifyObservers();
}
