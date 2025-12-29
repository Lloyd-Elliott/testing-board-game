package src.applicationcode.Rules;

import src.applicationcode.Player.Player;
import src.applicationcode.Board.BoardStrategy;

public class BounceRules implements RulesStrategy {
    
    @Override
    public MoveResult calculateMove(Player player, int diceRoll, BoardStrategy board, Player[] allPlayers) {
        int oldPosition = player.getPosition();
        int boardSize = board.getBoardSize();
        int tailSize = board.getTailSize();
        
        if (player.isInTail()) {
            int currentTailPos = player.getTailPosition();
            int newTailPosition = currentTailPos + diceRoll;
            
            if (newTailPosition >= tailSize) {
                return new MoveResult(oldPosition, boardSize + tailSize, false, tailSize, true, false);
            } else {
                return new MoveResult(oldPosition, boardSize + newTailPosition, false, newTailPosition, false, false);
            }
        } else {
            int startPos = player.getStartingPosition();
            int newPosition = oldPosition + diceRoll;
            
            if (newPosition >= boardSize) {
                int movePosition = newPosition % boardSize;
                
                if (movePosition >= startPos) {
                    player.setCompletedLap(true);
                    int overflow = movePosition - startPos;
                    
                    if (overflow >= tailSize) {
                        return new MoveResult(oldPosition, boardSize + tailSize, true, tailSize, true, false);
                    } else {
                        // Check collision before entering tail
                        int targetPosition = boardSize + overflow;
                        if (isPositionOccupied(targetPosition, allPlayers, player, boardSize)) {
                            return new MoveResult(oldPosition, oldPosition, false, 0, false, false, true, targetPosition);
                        }
                        return new MoveResult(oldPosition, targetPosition, true, overflow, false, false);
                    }
                } else {
                    player.setCompletedLap(true);
                    if (isPositionOccupied(movePosition, allPlayers, player, boardSize)) {
                        return new MoveResult(oldPosition, oldPosition, false, 0, false, false, true, movePosition);
                    }
                    return new MoveResult(oldPosition, movePosition, false, 0, false, false);
                }
            } else {
                if (player.hasCompletedLap() && oldPosition < startPos && newPosition >= startPos) {
                    int overflow = newPosition - startPos;
                    
                    if (overflow >= tailSize) {
                        return new MoveResult(oldPosition, boardSize + tailSize, true, tailSize, true, false);
                    } else {
                        int targetPosition = boardSize + overflow;
                        if (isPositionOccupied(targetPosition, allPlayers, player, boardSize)) {
                            return new MoveResult(oldPosition, oldPosition, false, 0, false, false, true, targetPosition);
                        }
                        return new MoveResult(oldPosition, targetPosition, true, overflow, false, false);
                    }
                } else {
                    // Check collision on normal move
                    if (isPositionOccupied(newPosition, allPlayers, player, boardSize)) {
                        return new MoveResult(oldPosition, oldPosition, false, 0, false, false, true, newPosition);
                    }
                    return new MoveResult(oldPosition, newPosition, false, 0, false, false);
                }
            }
        }
    }
    
    private boolean isPositionOccupied(int position, Player[] allPlayers, Player currentPlayer, int boardSize) {
        // Don't check collisions for tail positions
        if (position >= boardSize) {
            return false;
        }
        
        for (Player p : allPlayers) {
            if (p != currentPlayer && p.getPosition() == position && !p.isInTail()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean hasWinner(Player player, BoardStrategy board) {
        return player.isInTail() && player.getTailPosition() >= board.getTailSize();
    }
}
