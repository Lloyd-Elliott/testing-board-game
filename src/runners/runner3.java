package src.runners;

import java.util.Arrays;
import java.util.List;

import src.applicationcode.Board.Board;
import src.applicationcode.Board.LargeBoard;
import src.applicationcode.Board.SmallBoard;
import src.applicationcode.Dice.Dice;
import src.applicationcode.Dice.OneDiceStrategy;
import src.applicationcode.Game.Game;
import src.applicationcode.Game.PlayGame;
import src.applicationcode.Player.Player;
import src.applicationcode.Player.PlayerFactory;
import src.applicationcode.Rules.BounceRules;
import src.applicationcode.Rules.Rules;
import src.infrastructurecode.BoardLogger;
import src.infrastructurecode.GamePlayConsoleLogger;
import src.infrastructurecode.PlayerMovementLogger;
import src.applicationcode.Rules.BasicRules;
import src.applicationcode.Dice.FixedDiceStrategy;

public class Runner3 {
    
    public static void run() {
        System.out.println("========== Runner 3: Fixed Dice, Basic Rules Scenario 3 - Red Wins 3 Turns==========\n");

        List<Integer> fixedRolls = Arrays.asList(8,2,3,4,9);
        
        Dice dice = new Dice(new FixedDiceStrategy(fixedRolls));
        Board board = new Board(new SmallBoard(dice, new BoardLogger()));
        Player[] players = PlayerFactory.createPlayers(board.getBoardSize(), "Red", "Blue");
        
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
