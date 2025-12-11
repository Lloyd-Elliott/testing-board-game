package src;

import src.applicationcode.Board.BoardStrategy;
import src.applicationcode.Board.SmallBoard;
import src.applicationcode.Dice.Dice;
import src.applicationcode.Dice.OneDiceStrategy;
import src.applicationcode.Game.Game;
import src.applicationcode.Game.GamePlayImpl;
import src.applicationcode.Player.Player;
import src.applicationcode.Player.PlayerFactory;
import src.applicationcode.Rules.BasicRules;
import src.infrastructurecode.BoardLogger;
import src.infrastructurecode.GamePlayConsoleLogger;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== Board Game Starting ===\n");
        
        // Create dice with one dice strategy
        Dice dice = new Dice(new OneDiceStrategy());
        
        // Create board with dice
        BoardStrategy board = new SmallBoard(dice);
        
        // Create players using factory
        Player[] players = PlayerFactory.createPlayers("Red", "Blue");
        
        // Create rules
        BasicRules rules = new BasicRules();
        
        // Create game aggregate
        Game game = new Game(board, players, rules);
        
        // Create gameplay orchestrator
        GamePlayImpl gamePlay = new GamePlayImpl(game);
        
        // Add observers for logging
        gamePlay.addObserver(new GamePlayConsoleLogger());
        board.addObserver(new BoardLogger());
        
        // Setup board and notify observers
        board.notifyObservers();
        
        
        
        // Play the game until there's a winner
        gamePlay.playUntilWinner();
    }
}
