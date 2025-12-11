package src.applicationcode.Rules;

import src.applicationcode.Player.Player;
import src.applicationcode.Board.SmallBoard;

public interface RulesStrategy {
    MoveResult calculateMove(Player player, int diceRoll, SmallBoard board);
    boolean hasWinner(Player player, SmallBoard board);
}
