package src.infrastructurecode;

public class PlayerMovementLogger implements PlayerMovementObserver {
    
    @Override
    public void onPlayerMoved(String playerName, int oldPosition, int newPosition) {
        System.out.println("[Infrastructure] Player '" + playerName + "' moved from position " 
                          + oldPosition + " to position " + newPosition);
    }
}
