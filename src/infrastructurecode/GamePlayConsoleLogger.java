package src.infrastructurecode;

import src.applicationcode.Game.GamePlayObserver;
import src.applicationcode.Player.Player;

public class GamePlayConsoleLogger implements GamePlayObserver {
    
    @Override
    public void onPlayerMoved(Player player, int oldPosition, int newPosition) {
        String positionInfo = getPositionInfo(newPosition);
        System.out.println("[Infrastructure] Player '" + player.getName() + "' moved from " 
                          + getPositionInfo(oldPosition) + " to " + positionInfo);
    }
    
    private String getPositionInfo(int position) {
        if (position >= 18) {
            int tailPos = position - 18 + 1;
            return "Tail position " + tailPos;
        } else {
            return "Tile " + (position + 1);
        }
    }
    
    @Override
    public void onGameOver(Player winner) {
        System.out.println("[Infrastructure] Game Over! Winner: " + winner.getName());
    }
    
    @Override
    public void onPlayerOvershot(Player player) {
        System.out.println("[Infrastructure] Player '" + player.getName() + "' overshot! Staying in place.");
    }
    
    @Override
    public void onTurnStarted(Player player, int diceRoll) {
        System.out.println("\n" + player.getName() + "'s turn - Rolled: " + diceRoll);
    }
    
    @Override
    public void onGameComplete(Player winner) {
        System.out.println("\n=== Game Complete ===");
        System.out.println("Winner: " + winner.getName());
    }
}
