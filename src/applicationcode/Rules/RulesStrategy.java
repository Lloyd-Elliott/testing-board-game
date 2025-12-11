package src.applicationcode.Rules;

import src.applicationcode.Player.Player;
import src.applicationcode.Board.BoardStrategy;

public interface RulesStrategy {
    MoveResult calculateMove(Player player, int diceRoll, BoardStrategy board);
    boolean hasWinner(Player player, BoardStrategy board);
}
