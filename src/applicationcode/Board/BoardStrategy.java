package src.applicationcode.Board;
import src.infrastructurecode.BoardObserver;
import src.applicationcode.Dice.Dice;

public interface BoardStrategy {
    void notifyObservers();
    void addObserver(BoardObserver observer);
    void removeObserver(BoardObserver observer);
    int getBoardSize();
    int getTailSize();
    int[] getBoard();
    int[] getTail();
    Dice getDice();
}
