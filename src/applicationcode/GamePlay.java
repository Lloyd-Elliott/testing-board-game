package src.applicationcode;

import src.applicationcode.Player.Player;

public interface GamePlay {
    void onPlayerMoved(Player player, int oldPosition, int newPosition);
}
