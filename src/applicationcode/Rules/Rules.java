package src.applicationcode.Rules;

import src.applicationcode.Player.Player;
import src.applicationcode.Board.BoardStrategy;

public class Rules implements RulesStrategy {
    private final RulesStrategy rulesStrategy;

    public Rules(RulesStrategy rulesStrategy) {
        this.rulesStrategy = rulesStrategy;
    }
    
    @Override
    public MoveResult calculateMove(Player player, int diceRoll, BoardStrategy board, Player[] allPlayers) {
        return rulesStrategy.calculateMove(player, diceRoll, board, allPlayers);
    }
    
    @Override
    public boolean hasWinner(Player player, BoardStrategy board) {
        return rulesStrategy.hasWinner(player, board);
    }
}
