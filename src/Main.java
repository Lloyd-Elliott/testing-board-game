package src;

import src.applicationcode.Board.BoardStrategy;
import src.applicationcode.Board.LargeBoard;
import src.applicationcode.Board.SmallBoard;
import src.applicationcode.Dice.Dice;
import src.applicationcode.Dice.OneDiceStrategy;
import src.applicationcode.Game.Game;
import src.applicationcode.Game.PlayGame;
import src.applicationcode.Player.Player;
import src.applicationcode.Player.PlayerFactory;
import src.applicationcode.Rules.BasicRules;
import src.applicationcode.Rules.CollisionRules;
import src.applicationcode.Rules.ExactEndRules;
import src.applicationcode.Rules.RulesStrategy;
import src.infrastructurecode.BoardLogger;
import src.infrastructurecode.GamePlayConsoleLogger;

public class Main {
    
    public static void main(String[] args) {
        
        
        Dice dice = new Dice(new OneDiceStrategy());
        
        BoardStrategy board = new LargeBoard(dice, new BoardLogger());
        
        Player[] players = PlayerFactory.createPlayers(board.getBoardSize(), "Red", "Blue", "Green", "Yellow");
        
        RulesStrategy rules = new CollisionRules();
        
        Game game = new Game(board, players, rules);
        
        PlayGame gamePlay = new PlayGame(game, new GamePlayConsoleLogger(board.getBoardSize()));
        
        gamePlay.playUntilWinner();
    }
}
