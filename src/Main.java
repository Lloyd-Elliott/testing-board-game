package src;
import java.util.List;

import src.applicationcode.board.Board;
import src.applicationcode.board.BoardStrategy;
import src.applicationcode.board.SmallBoard;
import src.applicationcode.dice.Dice;
import src.applicationcode.dice.FixedDiceStrategy;
import src.applicationcode.dice.TwoDiceStrategy;
import src.applicationcode.game.Game;
import src.applicationcode.game.GamePlay;
import src.applicationcode.player.Player;
import src.applicationcode.player.PlayerFactory;
import src.applicationcode.rules.EndRulesStrategy;
import src.applicationcode.rules.Rules;

public class Main {
    public static void main(String[] args) {

        // Create players using factory
        Player p1 = PlayerFactory.createPlayer("P1");
        Player p2 = PlayerFactory.createPlayer("P2");

        List <Integer> fixedRolls = List.of(3, 4, 5, 6, 2, 1, 3, 4, 5, 6, 2, 1);

        

        // Choose any board type here:
        BoardStrategy small = new SmallBoard(p1, p2);
        // BoardStrategy medium = new MediumBoard();
        // BoardStrategy large = new LargeBoard();



        // Wrap the chosen board strategy inside Board
        Board board = new Board(small);

        // Rules apply to ANY board
        Rules rules = new Rules(new EndRulesStrategy(board));

        // Dice
        Dice dice = new Dice(new TwoDiceStrategy());
        Dice diceFixed = new Dice(new FixedDiceStrategy(fixedRolls));


        // Create the game
        Game game = new Game(dice, board, rules);

        Game game2 = new Game(diceFixed, board, rules);

        // Gameplay handler
        GamePlay gameplay = new GamePlay(game2);

        // Start the game!
        gameplay.playGame();
    }
}
