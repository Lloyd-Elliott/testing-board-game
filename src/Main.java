package src;

import src.applicationcode.Board.Board;
import src.applicationcode.Board.LargeBoard;
import src.applicationcode.Board.SmallBoard;
import src.applicationcode.Dice.Dice;
import src.applicationcode.Dice.OneDiceStrategy;
import src.applicationcode.Game.Game;
import src.applicationcode.Game.PlayGame;
import src.applicationcode.Player.Player;
import src.applicationcode.Player.PlayerFactory;
import src.applicationcode.Rules.BasicRules;
import src.applicationcode.Rules.BounceRules;
import src.applicationcode.Rules.ExactEndRules;
import src.applicationcode.Rules.Rules;
import src.infrastructurecode.BoardLogger;
import src.infrastructurecode.GamePlayConsoleLogger;
import src.infrastructurecode.PlayerMovementLogger;

public class Main {
    
    public static void main(String[] args) {
        
        
        Dice dice = new Dice(new OneDiceStrategy());
        
        Board board = new Board(new LargeBoard(dice, new BoardLogger()));
        
        Player[] players = PlayerFactory.createPlayers(board.getBoardSize(), "Red", "Blue", "Green", "Yellow");
        
        PlayerMovementLogger movementLogger = new PlayerMovementLogger(board.getBoardSize());
        for (Player player : players) {
            player.addObserver(movementLogger);
        }
        
        Rules rules = new Rules(new BounceRules());
        
        Game game = new Game(board, players, rules);
        
        PlayGame gamePlay = new PlayGame(game);
        
        GamePlayConsoleLogger gameLogger = new GamePlayConsoleLogger(board.getBoardSize());
        gamePlay.addObserver(gameLogger);
        
        gamePlay.playUntilWinner();
    }
}
