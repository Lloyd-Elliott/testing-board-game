package src.applicationcode.rules;

import src.applicationcode.board.Board;
import src.applicationcode.player.Player;

public class EndRulesStrategy implements RulesStrategy{

    private final Board board;

    public EndRulesStrategy(Board board) {
        this.board = board;
    }



    @Override
    public void endOfGame() {
        // Check for exact landing on winning distance
        int spacesTraveled1 = board.getSpacesTraveled1();
        int spacesTraveled2 = board.getSpacesTraveled2();
        int winningDistance = board.getEndIndex1();

        if (spacesTraveled1 == winningDistance) {
            System.out.println("P1 WINS! Landed exactly on the final tail space!");
            board.setGameOver(true);
        } else if (spacesTraveled2 == winningDistance) {
            System.out.println("P2 WINS! Landed exactly on the final tail space!");
            board.setGameOver(true);
        }
    }



    @Override
    public int calculateNewPosition(Player player, int moves, int winningDistance) {
        // Get current spaces traveled
        int currentSpacesTraveled = (player.getName().equals("P1")) ? 
            board.getSpacesTraveled1() : board.getSpacesTraveled2();
        
        int newSpacesTraveled = currentSpacesTraveled + moves;

        System.out.println(player.getName() + " rolled a " + moves);
        System.out.println(player.getName() + " has traveled " + currentSpacesTraveled + " spaces");
        
        // Overshoot rule: stay at previous position (must land exactly)
        if (newSpacesTraveled > winningDistance) {
            System.out.println(player.getName() + " would overshoot (" + newSpacesTraveled + " > " + winningDistance + ") - must land exactly. Stays at " + currentSpacesTraveled);
            return currentSpacesTraveled; // Stay put
        } else if (newSpacesTraveled == winningDistance) {
            System.out.println(player.getName() + " landed EXACTLY on the final tail space!");
        } else {
            System.out.println(player.getName() + " moves to " + newSpacesTraveled + " spaces traveled");
        }
        
        return newSpacesTraveled;
    }
}
