package src.applicationcode.Game;

import src.applicationcode.Player.Player;

public interface GamePlayObserver {
    void onRulesType(String rulesType);
    void onPlayerOvershot(Player player);
    void onPlayerCollision(Player player, int blockedPosition);
    void onGameOver(Player winner);
    void onTurnStarted(Player player, int diceRoll);
    void onGameComplete(Player winner, Player[] allPlayers);
}
