package src.applicationcode.Rules;

import src.applicationcode.Player.Player;
import src.applicationcode.Board.SmallBoard;

public class BasicRules implements RulesStrategy {
    
    @Override
    public MoveResult calculateMove(Player player, int diceRoll, SmallBoard board) {
        int oldPosition = player.getPosition();
        int boardSize = board.getBoardSize();
        int tailSize = board.getTailSize();
        
        if (player.isInTail()) {
            // Player is in tail section
            int currentTailPos = player.getTailPosition();
            int newTailPosition = currentTailPos + diceRoll;
            
            if (newTailPosition >= tailSize) {
                // Player has completed the game!
                return new MoveResult(oldPosition, boardSize + tailSize, false, tailSize, true);
            } else {
                // Still moving through tail
                return new MoveResult(oldPosition, boardSize + newTailPosition, false, newTailPosition, false);
            }
        } else {
            // Player is on main board
            int newPosition = oldPosition + diceRoll;
            
            if (newPosition >= boardSize) {
                // Player completes lap and enters tail
                int overflow = newPosition - boardSize;
                
                if (overflow >= tailSize) {
                    // Player completes game in one move
                    return new MoveResult(oldPosition, boardSize + tailSize, true, tailSize, true);
                } else {
                    // Player enters tail
                    return new MoveResult(oldPosition, boardSize + overflow, true, overflow, false);
                }
            } else {
                // Normal move on board
                return new MoveResult(oldPosition, newPosition, false, 0, false);
            }
        }
    }
    
    @Override
    public boolean hasWinner(Player player, SmallBoard board) {
        return player.isInTail() && player.getTailPosition() >= board.getTailSize();
    }
}
