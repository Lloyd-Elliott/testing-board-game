package src.applicationcode.Game;

import src.applicationcode.Player.Player;

public interface GamePlay {
    void onPlayerMoved(Player player, int oldPosition, int newPosition);
}
