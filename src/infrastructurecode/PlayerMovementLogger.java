package src.infrastructurecode;

import src.applicationcode.Player.PlayerMovementObserver;

public class PlayerMovementLogger implements PlayerMovementObserver {
    
    private final int boardSize;
    
    public PlayerMovementLogger(int boardSize) {
        this.boardSize = boardSize;
    }
    
    @Override
    public void onPlayerMoved(String playerName, int oldPosition, int newPosition) {
        System.out.println("[Infrastructure] Player " + playerName + " moved from " 
                          + getPositionInfo(oldPosition) + " to " + getPositionInfo(newPosition));
    }
    
    private String getPositionInfo(int position) {
        if (position >= boardSize) {
            int tailPos = position - boardSize;
            return "Tail position " + (tailPos + 1);
        } else {
            return "Tile " + (position + 1);
        }
    }
}
