package src.applicationcode.Board;
import src.applicationcode.Dice.Dice;
import java.util.ArrayList;
import java.util.List;

public class SmallBoard implements BoardStrategy {

    private final List<BoardObserver> observers = new ArrayList<>();
    private final Dice dice;
    private static final int BOARD_SIZE = 18;
    private static final int[] board = new int[]{
            1,2,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18
    };

    private static final int TAIL_SIZE = 3;
    private static final int[] tail = new int[]{
        1,2,3
    };
    
    public SmallBoard(Dice dice, BoardObserver observer) {
        this.dice = dice;
        // Register the injected observer
        if (observer != null) {
            addObserver(observer);
        }
        // Notify observers about board creation
        notifyObservers();
    }

    @Override
    public void addObserver(BoardObserver observer) {
        observers.add(observer);
    }

    @Override    
    public void removeObserver(BoardObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (BoardObserver observer : observers) {
            observer.onBoardCreated("SmallBoard", "2");
        }
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }
    
    public int getTailSize() {
        return TAIL_SIZE;
    }
    
    public int[] getBoard() {
        return board;
    }
    
    public int[] getTail() {
        return tail;
    }
    
    public Dice getDice() {
        return dice;
    }
}
