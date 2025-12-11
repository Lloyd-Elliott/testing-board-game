package src.applicationcode.rules;

import src.applicationcode.player.Player;

public interface RulesStrategy {
    void endOfGame();
     int calculateNewPosition(Player player, int moves, int endIndex);
}
