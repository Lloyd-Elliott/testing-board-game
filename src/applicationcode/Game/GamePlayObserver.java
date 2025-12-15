package src.applicationcode.Game;

import src.applicationcode.Player.Player;

public interface GamePlayObserver {
    void onPlayerMoved(Player player, int oldPosition, int newPosition);
    void onPlayerOvershot(Player player);
    void onGameOver(Player winner);
    void onTurnStarted(Player player, int diceRoll);
    void onGameComplete(Player winner);
}
