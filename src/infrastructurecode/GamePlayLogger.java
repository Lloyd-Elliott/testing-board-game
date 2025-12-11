package src.infrastructurecode;

import src.applicationcode.Game.GamePlay;
import src.applicationcode.Player.Player;

public class GamePlayLogger implements GamePlay {
    
    @Override
    public void onPlayerMoved(Player player, int oldPosition, int newPosition) {
        System.out.println("[Infrastructure] Player '" + player.getName() + "' moved from position " 
                          + oldPosition + " to position " + newPosition);
    }
}
