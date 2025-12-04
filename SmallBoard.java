import java.util.ArrayList;
import java.util.List;

public class SmallBoard implements BoardStratergy {

    private final Player player1 = new Player("P1");
    private final Player player2 = new Player("P2");

    private boolean gameOver = false;

    private final static int[] board = new int[]{
            1,2,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18,
            1,2,3,10,11,12,13,14,15,
            16,17,18,1,2,3,4,5,6,7,
            8,9,1,2,3
    };

    private int currentIndex1 = 0;
    private int currentIndex2 = 21;

    public int getCurrentIndex1() { return currentIndex1; }
    public int getCurrentIndex2() { return currentIndex2; }

    private final int endTail1 = 20;
    private final int endTail2 = 41;

    public int getEndIndex1() { return endTail1; }
    public int getEndIndex2() { return endTail2; }

    public int getTileValue(int index) {
        if (index >= 0 && index < board.length) {
            return board[index];
        }
        return -1; // Invalid index
    }

    private final List<BoardObserver> observers = new ArrayList<>();

    @Override
    public void setupBoard() {
        currentIndex1 = 0;
        currentIndex2 = 21;
        player1.setStartingPosition(0);
        player2.setStartingPosition(21);
        player1.setTurns(0);
        player2.setTurns(0);
        gameOver = false;

        System.out.println("Setting up board...");
    }

   @Override
    public void movePlayer(int moves, RulesStratergy rulesStratergy) {
        if (gameOver) return;
        
        Player currentPlayer = (player1.getTurns() <= player2.getTurns()) ? player1 : player2;
        int endIndex = (currentPlayer == player1) ? endTail1 : endTail2;
        
        // Rules calculate where to move
        int newPos = rulesStratergy.calculateNewPosition(currentPlayer, moves, endIndex);
        
        // Player updates itself
        currentPlayer.moveTo(newPos);
        
        // Update board tracking and notify
        if (currentPlayer == player1) currentIndex1 = newPos;
        else currentIndex2 = newPos;
        
        notifyObservers();
    }

    @Override
    public boolean hasWinner() {
        return gameOver;
    }

    public void setGameOver(boolean value) {
        this.gameOver = value;
    }

    @Override
    public void addObserver(BoardObserver obs) {
        observers.add(obs);
    }

    @Override
    public void notifyObservers() {
        for (BoardObserver obs : observers)
            obs.onBoardChanged();
    }
}
