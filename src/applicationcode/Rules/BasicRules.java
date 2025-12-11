package src.applicationcode.Rules;

import src.applicationcode.Player.Player;
import src.applicationcode.Board.BoardStrategy;

public class BasicRules implements RulesStrategy {
    
    @Override
    public MoveResult calculateMove(Player player, int diceRoll, BoardStrategy board) {
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
            int startPos = player.getStartingPosition();
            int newPosition = oldPosition + diceRoll;
            
            // Check if player has completed a full lap (traveled 18+ spaces from start)
            // or if they're wrapping around and passing/reaching their starting position
            if (newPosition >= boardSize) {
                // Calculate where they would land after wrapping
                int wrappedPosition = newPosition % boardSize;
                
                // Check if they've completed a lap by reaching/passing their starting position
                if (wrappedPosition >= startPos) {
                    // They've completed the lap and can enter tail
                    player.setCompletedLap(true);
                    int overflow = wrappedPosition - startPos;
                    
                    if (overflow >= tailSize) {
                        // Player completes game in one move
                        return new MoveResult(oldPosition, boardSize + tailSize, true, tailSize, true);
                    } else {
                        // Player enters tail
                        return new MoveResult(oldPosition, boardSize + overflow, true, overflow, false);
                    }
                } else {
                    // Not yet at starting position, continue around the board
                    player.setCompletedLap(true); // Mark that they've wrapped
                    return new MoveResult(oldPosition, wrappedPosition, false, 0, false);
                }
            } else {
                // Check if player has completed lap and is now crossing their starting position
                if (player.hasCompletedLap() && oldPosition < startPos && newPosition >= startPos) {
                    // Player can now enter the tail
                    int overflow = newPosition - startPos;
                    
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
    }
    
    @Override
    public boolean hasWinner(Player player, BoardStrategy board) {
        return player.isInTail() && player.getTailPosition() >= board.getTailSize();
    }
}
