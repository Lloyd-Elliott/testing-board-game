package src.applicationcode.board;
import java.util.ArrayList;
import java.util.List;

import src.applicationcode.player.Player;
import src.applicationcode.rules.RulesStrategy;

public class SmallBoard implements BoardStrategy {

    private final Player player1;
    private final Player player2;

    private boolean gameOver = false;

    public SmallBoard(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    // Circular board with 18 spaces
    private final static int BOARD_SIZE = 18;
    private final static int[] board = new int[]{
            1,2,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18
    };

    // Tail section - must complete after finishing the circle
    private final static int TAIL_SIZE = 3;
    private final static int[] tail = new int[]{
        1,2,3
    };

    // Track how many spaces each player has traveled
    private int spacesTraveled1 = 0;
    private int spacesTraveled2 = 0;
    
    // Players must complete the circle (18 spaces) + tail (3 spaces) to win
    private final int WINNING_DISTANCE = BOARD_SIZE + TAIL_SIZE;

    // Get current position - if past BOARD_SIZE, they're in the tail
    public int getCurrentIndex1() { 
        if (spacesTraveled1 >= BOARD_SIZE) {
            return BOARD_SIZE + (spacesTraveled1 - BOARD_SIZE); // In tail section
        }
        return spacesTraveled1 % BOARD_SIZE; 
    }
    public int getCurrentIndex2() { 
        if (spacesTraveled2 >= BOARD_SIZE) {
            return BOARD_SIZE + (spacesTraveled2 - BOARD_SIZE); // In tail section
        }
        return (spacesTraveled2 + BOARD_SIZE / 2) % BOARD_SIZE; 
    }

    // Get spaces traveled (for win condition)
    public int getSpacesTraveled1() { return spacesTraveled1; }
    public int getSpacesTraveled2() { return spacesTraveled2; }

    public int getEndIndex1() { return WINNING_DISTANCE; }
    public int getEndIndex2() { return WINNING_DISTANCE; }

    public int getTileValue(int index) {
        if (index >= BOARD_SIZE) {
            // In tail section
            int tailIndex = index - BOARD_SIZE;
            if (tailIndex >= 0 && tailIndex < tail.length) {
                return tail[tailIndex];
            }
        } else if (index >= 0 && index < board.length) {
            return board[index % BOARD_SIZE];
        }
        return -1; // Invalid index
    }

    private final List<BoardObserver> observers = new ArrayList<>();

    @Override
    public void setupBoard() {
        spacesTraveled1 = 0;
        spacesTraveled2 = 0;
        player1.setStartingPosition(0);  // P1 starts at position 0 on circle
        player2.setStartingPosition(BOARD_SIZE / 2);  // P2 starts at position 21 on circle
        player1.setTurns(0);
        player2.setTurns(0);
        gameOver = false;

        System.out.println("Setting up circular board (" + BOARD_SIZE + " spaces) + tail (" + TAIL_SIZE + " spaces)...");
        System.out.println("P1 starts at index 0, P2 starts at index " + (BOARD_SIZE / 2));
        System.out.println("Players must complete the circle (" + BOARD_SIZE + " spaces) then enter the tail (" + TAIL_SIZE + " spaces) to win");
        System.out.println("Total distance to win: " + WINNING_DISTANCE + " spaces");
    }

   @Override
    public void movePlayer(int moves, RulesStrategy rulesStrategy) {
        if (gameOver) return;
        
        Player currentPlayer = (player1.getTurns() <= player2.getTurns()) ? player1 : player2;
        boolean isPlayer1 = (currentPlayer == player1);
        
        // Rules calculate new total spaces traveled
        int newSpacesTraveled = rulesStrategy.calculateNewPosition(currentPlayer, moves, WINNING_DISTANCE);
        
        // Calculate actual position (circle or tail)
        int actualPosition;
        if (newSpacesTraveled >= BOARD_SIZE) {
            // Entered the tail - position is BOARD_SIZE + tail offset
            actualPosition = BOARD_SIZE + (newSpacesTraveled - BOARD_SIZE);
            if (newSpacesTraveled == BOARD_SIZE) {
                System.out.println(currentPlayer.getName() + " has completed the circle and entered the TAIL!");
            }
        } else {
            // Still on circular board
            actualPosition = isPlayer1 ? 
                newSpacesTraveled % BOARD_SIZE : 
                (newSpacesTraveled + BOARD_SIZE / 2) % BOARD_SIZE;
        }
        
        // Update player position and board tracking
        currentPlayer.moveTo(actualPosition);
        
        if (isPlayer1) {
            spacesTraveled1 = newSpacesTraveled;
        } else {
            spacesTraveled2 = newSpacesTraveled;
        }
        
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
