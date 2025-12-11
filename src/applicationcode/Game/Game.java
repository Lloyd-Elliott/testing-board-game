package src.applicationcode.Game;
import src.applicationcode.Dice.Dice;
import src.applicationcode.Board.Board;
import src.applicationcode.Rules.Rules;

public class Game {
    private Dice dice;
    private Board board;
    private Rules rules;

    public Game(Dice dice, Board board, Rules rules) {
        this.dice = dice;
        this.board = board;
        this.rules = rules;
    }

}
