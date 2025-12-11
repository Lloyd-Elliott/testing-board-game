package src.applicationcode.game;

import src.applicationcode.board.Board;
import src.applicationcode.board.BoardObserver;
import src.applicationcode.dice.Dice;
import src.applicationcode.rules.Rules;

public class Game {
    public Dice dice;
    public Board board;
    public Rules rules;

    public Game(Dice dice, Board board, Rules rules) {
        this.dice = dice;
        this.board = board;
        this.rules = rules;

        BoardObserver observer = new BoardObserver(board, rules);
        board.addObserver(observer);
    }
}

