package src.applicationcode.board;

import src.applicationcode.rules.RulesStrategy;

public interface BoardStrategy {
    void setupBoard();
    void movePlayer(int moves, RulesStrategy rulesStrategy);
    boolean hasWinner();

    int getCurrentIndex1();
    int getCurrentIndex2();
    int getEndIndex1();
    int getEndIndex2();
    int getSpacesTraveled1();
    int getSpacesTraveled2();
    void setGameOver(boolean value);

    int getTileValue(int index);

    void addObserver(BoardObserver obs);
    void notifyObservers();
}
