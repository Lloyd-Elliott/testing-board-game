package src.applicationcode;

import src.applicationcode.Board.SmallBoard;
import src.applicationcode.Dice.Dice;
import src.applicationcode.Dice.OneDiceStrategy;
import src.applicationcode.Player.Player;
import src.applicationcode.Rules.BasicRules;
import src.infrastructurecode.BoardLogger;
import src.infrastructurecode.GamePlayConsoleLogger;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== Board Game Starting ===\n");
        
        // Create dice with one dice strategy
        Dice dice = new Dice(new OneDiceStrategy());
        
        // Create board with dice
        SmallBoard board = new SmallBoard(dice);
        
        // Create players
        Player redPlayer = new Player("Red");
        Player bluePlayer = new Player("Blue");
        Player[] players = {redPlayer, bluePlayer};
        
        // Create rules
        BasicRules rules = new BasicRules();
        
        // Create gameplay
        GamePlayImpl game = new GamePlayImpl(board, players, rules);
        
        // Add observers for logging
        game.addObserver(new GamePlayConsoleLogger());
        board.addObserver(new BoardLogger());
        
        // Setup board and notify observers
        board.notifyObservers();
        
        
        
        // Play the game until there's a winner
        game.playUntilWinner();
    }
}
