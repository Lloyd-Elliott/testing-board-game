package src.runners;
import java.util.Arrays;
import java.util.List;

import src.applicationcode.Board.Board;
import src.applicationcode.Board.LargeBoard;
import src.applicationcode.Dice.Dice;
import src.applicationcode.Dice.TwoDiceStrategy;
import src.applicationcode.Dice.FixedDiceStrategy;
import src.applicationcode.Board.SmallBoard;
import src.applicationcode.Game.Game;
import src.applicationcode.Game.PlayGame;
import src.applicationcode.Player.Player;
import src.applicationcode.Player.PlayerFactory;
import src.applicationcode.Rules.ExactEndRules;
import src.applicationcode.Rules.Rules;
import src.infrastructurecode.BoardLogger;
import src.infrastructurecode.GamePlayConsoleLogger;
import src.infrastructurecode.PlayerMovementLogger;
import src.applicationcode.Rules.BasicRules;

public class Runner7{
    
    public static void run() {
        System.out.println("========== Runner 7 Fixed Dice, Large Board Basic Rules Scenario 7 - Yellow Wins 6 Turns==========\n");


        List<Integer> fixedRolls = Arrays.asList(7,3,8,5,7,6,8,7,6,8,2,4,4,8,5,7,8,3,9,9,7,5,7,9);
        
        Dice dice = new Dice(new FixedDiceStrategy(fixedRolls));
        Board board = new Board(new LargeBoard(dice, new BoardLogger()));
        Player[] players = PlayerFactory.createPlayers(board.getBoardSize(), "Red", "Blue", "Green", "Yellow");
        
        PlayerMovementLogger movementLogger = new PlayerMovementLogger(board.getBoardSize());
        for (Player player : players) {
            player.addObserver(movementLogger);
        }
        
        Rules rules = new Rules(new BasicRules());
        Game game = new Game(board, players, rules);
        PlayGame gamePlay = new PlayGame(game);
        
        GamePlayConsoleLogger gameLogger = new GamePlayConsoleLogger(board.getBoardSize());
        gamePlay.addObserver(gameLogger);
        
        gamePlay.playUntilWinner();
    }
}
