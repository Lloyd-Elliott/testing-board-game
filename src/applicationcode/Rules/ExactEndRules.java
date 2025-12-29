package src.applicationcode.Rules;

import src.applicationcode.Board.BoardStrategy;
import src.applicationcode.Player.Player;

public class ExactEndRules implements RulesStrategy {

    @Override
    public MoveResult calculateMove(Player player, int diceRoll, BoardStrategy board, Player[] allPlayers) {
        int oldPosition = player.getPosition();
        int boardSize = board.getBoardSize();
        int tailSize = board.getTailSize();
        
        if (player.isInTail()) {
            int currentTailPos = player.getTailPosition();
            int newTailPosition = currentTailPos + diceRoll;
            
            if (newTailPosition == tailSize) {
                return new MoveResult(oldPosition, boardSize + tailSize, false, tailSize, true, false);
            } else if (newTailPosition > tailSize) {
                return new MoveResult(oldPosition, oldPosition, false, currentTailPos, false, true);
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
                    
                    if (overflow > tailSize) {
                        return new MoveResult(oldPosition, startPos, false, 0, false, true);
                    } else if (overflow == tailSize) {
                        return new MoveResult(oldPosition, boardSize + tailSize, true, tailSize, true, false);
                    } else {
                        return new MoveResult(oldPosition, boardSize + overflow, true, overflow, false, false);
                    }
                } else {
                    player.setCompletedLap(true);
                    return new MoveResult(oldPosition, movePosition, false, 0, false, false);
                }
            } else {
                if (player.hasCompletedLap() && oldPosition < startPos && newPosition >= startPos) {
                    int overflow = newPosition - startPos;
                    
                    if (overflow > tailSize) {
                        return new MoveResult(oldPosition, startPos, false, 0, false, true);
                    } else if (overflow == tailSize) {
                        return new MoveResult(oldPosition, boardSize + tailSize, true, tailSize, true, false);
                    } else {
                        return new MoveResult(oldPosition, boardSize + overflow, true, overflow, false, false);
                    }
                } else {
                    return new MoveResult(oldPosition, newPosition, false, 0, false, false);
                }
            }
        }
    }
    
    @Override
    public boolean hasWinner(Player player, BoardStrategy board) {
        return player.isInTail() && player.getTailPosition() == board.getTailSize();
    }
}
   

