package src.infrastructurecode;

import src.applicationcode.GamePlayImpl;
import src.applicationcode.Player.Player;

public class GamePlayConsoleLogger implements GamePlayImpl.GamePlayObserver {
    
    @Override
    public void onPlayerMoved(Player player, int oldPosition, int newPosition) {
        System.out.println("[Infrastructure] Player '" + player.getName() + "' moved from position " 
                          + oldPosition + " to position " + newPosition);
    }
    
    @Override
    public void onGameOver(Player winner) {
        System.out.println("[Infrastructure] Game Over! Winner: " + winner.getName());
    }
}
